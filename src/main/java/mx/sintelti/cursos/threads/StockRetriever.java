package mx.sintelti.cursos.threads;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import java.io.IOException;
import java.math.BigDecimal;

public class StockRetriever implements Runnable {

    private String Company;
    static BigDecimal sumaPrecio = new BigDecimal("0");

    public StockRetriever(String company) {
       this.Company = company;
    }

    public BigDecimal getStockPrice() throws IOException {

        Stock stock = YahooFinance.get(Company);
        BigDecimal price=stock.getQuote().getPrice();
        return price;
    }

    @Override
    public void run(){

        try {
            BigDecimal precio = getStockPrice();
            sumaPrecio = sumaPrecio.add(precio);
        }catch (IOException e){
            System.out.println(e);
        }
        System.out.println("La suma de los precios es: "+sumaPrecio);
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }


}
