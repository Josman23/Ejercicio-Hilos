package mx.sintelti.cursos.threads;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import java.io.IOException;
import java.math.BigDecimal;

public class StockRetriever implements Runnable {

    private String company;
    static BigDecimal sumaPrecio = new BigDecimal("0");

    public StockRetriever(String company) {
       this.company = company;
    }

    @Override
    public void run(){
        try {
            Stock stock = YahooFinance.get(this.company);
            BigDecimal price=stock.getQuote().getPrice();
            sumaPrecio = sumaPrecio.add(price);
        }catch (IOException e){
            System.out.println(e);
        }
        System.out.println("La suma de los precios es: "+sumaPrecio);
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
