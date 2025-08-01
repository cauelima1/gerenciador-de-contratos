package contratos.model;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "contract")
@Entity(name = "tb_clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long contract;
    private String name;
    private Long id; //cpf ou cnjp
    private String date;
    private Long foneNumber;
    private String email;
    private String location;

    @Column(columnDefinition = "TEXT")
    private String serviceDetails;

    private String serviceType;
    private Long servicePrice;
    private String servicePriceFormated;
    private Long deslocationPrice;
    private String deslocationPriceFormated;
    private Long totalPrice;
    private String totalPriceFormated;

    public String getTotalPriceFormated() {
        return totalPriceFormated;
    }

    public void setTotalPriceFormated(String totalPriceFormated) {
        this.totalPriceFormated = totalPriceFormated;
    }

    public String getDeslocationPriceFormated() {
        return deslocationPriceFormated;
    }

    public void setDeslocationPriceFormated(String deslocationPriceFormated) {
        this.deslocationPriceFormated = deslocationPriceFormated;
    }

    public String getServicePriceFormated() {
        return servicePriceFormated;
    }

    public void setServicePriceFormated(String servicePriceFormated) {
        this.servicePriceFormated = servicePriceFormated;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Client(Long totalPrice) {
        this.totalPrice = totalPrice;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Long getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(Long servicePrice) {
        this.servicePrice = servicePrice;
    }

    public Long getDeslocationPrice() {
        return deslocationPrice;
    }

    public void setDeslocationPrice(Long deslocationPrice) {
        this.deslocationPrice = deslocationPrice;
    }


    public Client() {
    }

    public Long getContract() {
        return contract;
    }

    public void setContract(Long contract) {
        this.contract = contract;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getFoneNumber() {
        return foneNumber;
    }

    public void setFoneNumber(Long foneNumber) {
        this.foneNumber = foneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getServiceDetails() {
        return serviceDetails;
    }

    public void setServiceDetails(String serviceDetails) {
        this.serviceDetails = serviceDetails;
    }

    public Client(Long contract, String name, Long id, String email, String date, Long foneNumber, String location, String serviceDetails, String serviceType, Long servicePrice, Long deslocationPrice) {
        this.contract = contract;
        this.email = email;
        this.name = name;
        this.id = id;
        this.date = date;
        this.foneNumber = foneNumber;
        this.location = location;
        this.serviceDetails = serviceDetails;
        this.serviceType = serviceType;
        this.servicePrice = servicePrice;
        this.deslocationPrice = deslocationPrice;
    }
}