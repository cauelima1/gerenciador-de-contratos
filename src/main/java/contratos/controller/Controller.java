package contratos.controller;

import contratos.model.Client;
import contratos.repository.ClientRepository;
import contratos.repository.UserRepository;
import contratos.service.ClientService;
import contratos.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private ClientService clientService;



    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginService loginService;



    @GetMapping("/index")
    public String homeIndex() {
        return "index";
    }

    @PostMapping("/NovoOrcamento")
    public String registerClient(@ModelAttribute Client client, BindingResult result, Model model){
        if (result.hasErrors()) {
            throw new IllegalArgumentException("Digite corretamente");
        } else{
             clientService.formatarValores(client);

            model.addAttribute("Mensagem","Cliente Salvo!");
            model.addAttribute("client", client);
            return "salvo";
        }
    }

    @GetMapping("/alterar")
    public String escolherEditarOuDeletar(){
        return "alterarEdeletar";
    }

    @GetMapping("/gerarPdfExistente")
    public String gerarPDFExistente() {
        return "gerarPdfJaExistente";
    }

    @PostMapping("/gerar-pdf-existente")
    public String gerarPdfExistente(@RequestParam Long numeroContrato, Model model){
        Client client = clientRepository.findByContract(numeroContrato);
        if (client == null){
            throw new RuntimeException("Número de contrato não identificado. Verifique por favor!");
        }
        model.addAttribute("Mensagem","Cliente Salvo!");
        model.addAttribute("client", client);
        return "salvo";
    }


    @DeleteMapping("/deletar/{contract}")
    public String deletarContrato(@PathVariable Long contract){
        clientRepository.delete(clientRepository.findByContract(contract));
        return "index";
    }

    @GetMapping("/gerarPDF")
    public String gerarPDF (Client client){
        return "gerarPDF";
    }

    @GetMapping("/clients/{contract}")
    public ResponseEntity<Client> getClient (@PathVariable Long contract){ //para validar se existe
        Client client = clientRepository.findByContract(contract);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return null;
        }
    }

    @GetMapping("/NovoOrcamento")
    public String novoOrcamento() {
        return "novoOrcamento";
    }

    @GetMapping("/edit/{contract}")
    public String mostrarFormularioEdicao(@PathVariable Long contract, Model model) {
        try {
            Client client = clientRepository.findByContract(contract);
            model.addAttribute("client", client);
        } catch (RuntimeException e) {
        }
        return "EditarOrcamento";
    }

    @PostMapping("/edit")
    public String editarCliente(@ModelAttribute Client client){
        clientService.gravarDadosEditados(client);
        return "redirect:/clients";
    }



    @GetMapping("/clients")
    public String showClients (Model model){
        List<Client> clientList = clientRepository.findAll();
        model.addAttribute("clients", clientList);
        System.out.println(clientList);
        return "clients";
    }

    @GetMapping("/.well-known/appspecific/com.chrome.devtools.json")
    public ResponseEntity<Void> ignoreChromeDevToolsRequest() {
        return ResponseEntity.noContent().build();
    }



}
