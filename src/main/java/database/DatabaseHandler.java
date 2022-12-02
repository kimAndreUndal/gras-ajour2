package database;

import io.agroal.api.AgroalDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandler {

private static final Logger logger = LoggerFactory.getLogger(DatabaseHandler.class);

    @Inject
    AgroalDataSource dataSource;

    public boolean insertIntoGrasLoan(String customerId,
                                      String providerId,
                                      String financialInstitutionId,
                                      String loanType,
                                      String receivedTime,
                                      String accountId,
                                      String accountName,
                                      float originalBalance,
                                      float balance,
                                      String terms,
                                      int interestTermsBalance,
                                      int nonInterestBearingBalance,
                                      int effectiveInterestRate,
                                      int nominalInterestRate,
                                      float installmentCharges,
                                      float installmentChargesPeriod,
                                      String coBorrower,
                                      float creditLimit,
                                      String processedTime,
                                      String processedTimeText
                                      ){
        String sql ="insert into gras.loan("+
                "customer_id, " +
                "provider_id, financial_institution_id, " +
                "loan_type, received_time, account_id, " +
                "account_name, original_balance, balance, " +
                "terms, interest_bearing_balance, " +
                "non_interest_bearing_balance, " +
                "effective_interest_rate, " +
                "nominal_interest_rate, " +
                "installment_charges, " +
                "installment_charges_period, " +
                "co_borrower, credit_limit, " +
                "processed_time, " +
                "processed_time_text, " +
                "delete_mark" +
                ") values (?, ?, ?, ?, NOW(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'false')";
        try(Connection conn = dataSource.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, customerId);
        }catch (SQLException e){
            logger.error("Loan: " + e.getMessage());
            return false;
        }
        return true;
    }
}
