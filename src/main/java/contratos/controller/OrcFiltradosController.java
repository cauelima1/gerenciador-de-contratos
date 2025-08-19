package contratos.controller;


import contratos.model.Client;
import contratos.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Contract;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrcFiltradosController {

    @Autowired
    private ClientRepository clientRepository;


    @GetMapping("/mostrarEmAberto")
    public String mostrarOrcamentosAberto(Model model){
        List<Client> clientList = clientRepository.findAll()
                .stream()
                .filter(c -> c.getServiceStatus().equals("0")).toList();
        Double totalEmAberto = clientList.stream().mapToDouble(Client::getTotalPrice).sum();


        String totalFormatado = String.format("R$ %.2f", totalEmAberto);
        model.addAttribute("totalFormatado", totalFormatado);
        model.addAttribute("clientes", clientList);
        return "orcamentosEmAberto";
    }

    @GetMapping("/mostrarAgendados")
    public String mostrarOrcamentosAgendados (Model model) {
        List<Client> clientList = clientRepository.findAll().stream().filter(
                c -> c.getServiceStatus().equals("1")).toList();

        Double totalAgendado = clientList.stream().mapToDouble(Client::getTotalPrice).sum();
        String totalAgendadoFormated = String.format("R$ %.2f", totalAgendado);
        model.addAttribute("totalAgendado", totalAgendadoFormated);
        model.addAttribute("clientes", clientList);
        return "orcamentosAgendados";
    }

    @GetMapping("/mostrarNaoAprovados")
    public String mostrarOrcNaoAprovados (Model model) {
        List<Client> clientList = clientRepository.findAll().stream().filter(
                c -> c.getServiceStatus().equals("2")).toList();

        Double totalNaoAprovado = clientList.stream().mapToDouble(Client::getTotalPrice).sum();
        String totalNaoAprovadoFormated = String.format("R$ %.2f", totalNaoAprovado);
        model.addAttribute("totalNaoAprovado", totalNaoAprovadoFormated);
        model.addAttribute("clientes", clientList);
        return "orcamentosNaoAprovados";
    }

    @GetMapping("/mostrarFinalizados")
    public String mostrarFinalizados (Model model) {
        List<Client> clientList = clientRepository.findAll().stream().filter(
                c -> c.getServiceStatus().equals("3")).toList();

        Double totalFinalizado = clientList.stream().mapToDouble(Client::getTotalPrice).sum();
        String totalFinalizadoFormated = String.format("R$ %.2f", totalFinalizado);
        model.addAttribute("totalFinalizado", totalFinalizadoFormated);
        model.addAttribute("clientes", clientList);
        return "orcamentosFinalizados";
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

}
