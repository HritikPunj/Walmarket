package comp3350.Innovator2.objects;

import java.util.List;

import comp3350.Innovator2.objects.utils.AccountType;

/**
 * Class to store seller information
 */
public class Seller extends User{
    private int sellerID;

    private BankingInfo debitDetails;

    public Seller(String firstName, String lastName, int sellerID, String username, String email, List<CreditCard> payInfo, BankingInfo debitDetails){
        super(firstName, lastName, username, email, payInfo);
        this.sellerID = sellerID;
        this.debitDetails = debitDetails;
    }

    public int getSellerID() {
        return sellerID;
    }

    public BankingInfo getDebitDetails() {
        return debitDetails;
    }

    public AccountType getAccountType() {
        return AccountType.Seller;
    }

    public void setDebitDetails(BankingInfo info){
        debitDetails = new BankingInfo(info.getAccountNumber(), info.getTransitCode(), info.getBranchCode());
    }
}

