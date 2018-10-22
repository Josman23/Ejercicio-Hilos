package mx.sintelti.cursos.threads;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import java.io.IOException;
import java.math.BigDecimal;

public class StockRetriever implements Runnable {

    private String company;

    public StockRetriever(String company) {
       this.company = company;
    }

    @Override
    public void run(){
        try {

            Stock stock = YahooFinance.get(this.company);
            BigDecimal price = stock.getQuote().getPrice();
            stock.print();
            TestStock.addPrice(price);
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}