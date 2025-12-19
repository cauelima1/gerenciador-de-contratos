package contratos.controller;

import contratos.model.Client;
import contratos.repository.ClientRepository;
import contratos.repository.UserRepository;
import contratos.service.ClientService;
import contratos.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Controller
public class MainController {

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
        try {
            clientService.formatarValores(client);
            model.addAttribute("Mensagem", "Cliente Salvo!");
            model.addAttribute("client", client);
            return "salvo";
        } catch (IllegalArgumentException e) {
            throw  new IllegalArgumentException("Digite os dados conrretamente!");
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

        if (clientRepository.findByContract(numeroContrato) == null){
            throw new RuntimeException("Número de contrato não identificado. Verifique por favor!");
        } else {
            Client client = clientRepository.findByContract(numeroContrato);
            model.addAttribute("Mensagem", "Cliente Salvo!");
            model.addAttribute("client", client);
            return "salvo";
        }
    }

    @DeleteMapping("/deletar/{contract}")
    public ResponseEntity<String> deletarContrato(@PathVariable Long contract){
        Client client = clientRepository.findByContract(contract);

        if (client != null) {
            clientRepository.delete(client);
            return ResponseEntity.ok("Contrato deletado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Contrato não encontrado.");
        }
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
            if (client==null){
                model.addAttribute("erroMensagem", "Contrato não encontrado: " + contract);
                return "alterarEdeletar";
            }
            model.addAttribute("client", client);
        }catch (Exception e) {
            model.addAttribute("erroMensagem", "Erro interno: " + e.getMessage());
            return "editarOrcamento";
        }
                return "editarOrcamento";
    }

    @PostMapping("/edit")
    public String editarCliente(@ModelAttribute Client client){
        clientService.gravarDadosEditados(client);
        return "redirect:/clients";
    }

    @GetMapping("/deleteById/{contract}")
    public ResponseEntity<?> getNameClientToDelete (@PathVariable Long contract){
        Client client = clientRepository.findByContract(contract);
        if (client == null){
            return ResponseEntity.notFound().build();
        }
        Map<String, String> response = new HashMap<>();
        response.put(String.valueOf(client.getContract()), client.getName());

        return ResponseEntity.ok(response);
    }


    @GetMapping("/clients")
    public String showContracts(Model model){
        List<Client> clientList= clientService.showAllContracts().stream()
                        .sorted(Comparator.comparing(Client::getContract).reversed()).toList();
        model.addAttribute("clients", clientList);
        System.out.println(clientList);
        return "clients";
    }

    @GetMapping("/.well-known/appspecific/com.chrome.devtools.json")
    public ResponseEntity<Void> ignoreChromeDevToolsRequest() {
        return ResponseEntity.noContent().build();
    }





}
