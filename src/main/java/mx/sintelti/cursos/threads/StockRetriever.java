package mx.sintelti.cursos.threads;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;

public class StockRetriever {

    private String Company;

    public StockRetriever(String company) {
       this.Company = company;
    }

    public BigDecimal getStockPrice() throws IOException {

        Stock stock = YahooFinance.get(Company);
        BigDecimal price=stock.getQuote().getPrice();
        return price;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

}
