package comp3350.Innovator2.objects;

public class BankingInfo {
    private long accountNumber;
    private int transitCode;
    private int branchCode;

    public BankingInfo(long accountNumber, int transitCode, int branchCode){
        this.accountNumber = accountNumber;
        this.branchCode = branchCode;
        this.transitCode = transitCode;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public int getBranchCode() {
        return branchCode;
    }

    public int getTransitCode() {
        return transitCode;
    }
}
