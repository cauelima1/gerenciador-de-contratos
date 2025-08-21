package contratos.service;

import contratos.model.Client;
import contratos.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public void gravarDadosEditados(Client client) {
        Client originClient = clientRepository.findByContract((long) client.getContract());
        originClient.setName(client.getName());
        originClient.setId(client.getId());
        originClient.setEmail(client.getEmail());
        originClient.setDate(client.getDate());
        originClient.setFoneNumber(client.getFoneNumber());
        originClient.setLocation(client.getLocation());
        originClient.setServiceDetails(client.getServiceDetails());
        originClient.setServiceType(client.getServiceType());
        originClient.setServicePrice(client.getServicePrice());
        originClient.setDeslocationPrice(client.getDeslocationPrice());
        originClient.setServiceStatus(client.getServiceStatus());
        formatarValores(originClient);
        clientRepository.save(originClient);
    }

    public void formatarValores(Client client) {
        DecimalFormat df = new DecimalFormat("R$ #,##0.00");
        client.setServicePriceFormated(df.format(client.getServicePrice()));
        if (client.getDeslocationPrice() == null){
            client.setDeslocationPrice(0L);
        }
        client.setDeslocationPriceFormated(df.format(client.getDeslocationPrice()));
        client.setTotalPrice(client.getDeslocationPrice()+client.getServicePrice());
        client.setTotalPriceFormated(df.format(client.getTotalPrice()));
        clientRepository.save(client);
    }

    public List<Client> showAllContracts () {
        Map<String, String> statusMap = Map.of(
                "0", "Aberto",
                "1", "Agendado",
                "2", "Finalizado",
                "3", "Não Aprovado"
        );
        List<Client> clientsList = clientRepository.findAll().stream().map(
                c -> {
                    String status = statusMap.getOrDefault(c.getServiceStatus(), "Status Desconhecido");
                    c.setServiceStatus(status);
                    return c;
                }).toList();
    return clientsList;
    }
}
