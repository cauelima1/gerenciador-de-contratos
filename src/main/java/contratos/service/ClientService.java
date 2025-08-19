package contratos.service;

import contratos.model.Client;
import contratos.repository.ClientRepository;
import contratos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public void gravarDadosEditados(Client client) {
        Client originClient = repository.findByContract((long) client.getContract());
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
        repository.save(originClient);
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
        repository.save(client);
    }

}
