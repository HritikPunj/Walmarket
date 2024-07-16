package comp3350.Innovator2.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import comp3350.Innovator2.R;
import comp3350.Innovator2.logic.ICartManager;
import comp3350.Innovator2.logic.IUserManager;
import comp3350.Innovator2.logic.StoreHelper;
import comp3350.Innovator2.logic.exceptions.UIException;
import comp3350.Innovator2.objects.CreditCard;
import comp3350.Innovator2.objects.utils.CardType;

public class PagePayment extends AppCompatActivity {

    private IUserManager userManager;

    Context context;
    EditText creditCardNum;
    EditText expiryDate;
    EditText cvv;
    RadioGroup creditCardType;
    EditText nameOfCardHolder;
    RadioButton masterCard;
    RadioButton visa;
    ConstraintLayout layout;
    PopupWindow popupWindow;
    CheckBox savePaymentCheckbox;
    static final int REQUEST_CODE = 1;

    TextView subtotalText;
    ICartManager cartManager;

    TextWatcher creditCardNumListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() != 16){
                creditCardNum.setTextColor(ContextCompat.getColor(context, R.color.red));
            } else {
                creditCardNum.setTextColor(ContextCompat.getColor(context, R.color.walmarketBlue));
            }
        }
    };

    TextWatcher expiryDateListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() != 4){
                expiryDate.setTextColor(ContextCompat.getColor(context, R.color.red));
            } else {
                expiryDate.setTextColor(ContextCompat.getColor(context, R.color.walmarketBlue));
            }
        }
    };

    TextWatcher cvvListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() != 3){
                cvv.setTextColor(ContextCompat.getColor(context, R.color.red));
            } else {
                cvv.setTextColor(ContextCompat.getColor(context, R.color.walmarketBlue));
            }
        }
    };

    RadioGroup.OnCheckedChangeListener creditCardTypeListener = (group, checkedId) -> {
        for (int i = 0; i< group.getChildCount(); i++){
            RadioButton radioButton =(RadioButton) group.getChildAt(i);
            radioButton.setChecked(radioButton.getId() == checkedId);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_view);

        context = this;

        creditCardNum = findViewById(R.id.creditCardNum);
        expiryDate = findViewById(R.id.expiryDate);
        cvv = findViewById(R.id.cvv);
        creditCardType = findViewById(R.id.creditCardType);
        nameOfCardHolder = findViewById(R.id.nameofCardHolder);
        masterCard = findViewById(R.id.masterCard);
        visa = findViewById(R.id.visa);
        layout = findViewById(R.id.layout);
        savePaymentCheckbox = findViewById(R.id.savePaymentCheckbox);
        subtotalText = findViewById(R.id.subtotal);

        cartManager = StoreHelper.getCartManager();

        creditCardNum.addTextChangedListener(creditCardNumListener);
        expiryDate.addTextChangedListener(expiryDateListener);
        cvv.addTextChangedListener(cvvListener);
        creditCardType.setOnCheckedChangeListener(creditCardTypeListener);

        displayTotal();

        userManager = StoreHelper.getUserManager();

        fillPaymentInfo();
    }

    @Override
    public void onResume ()
    {
        super.onResume();
        fillPaymentInfo();
    }

    private void fillPaymentInfo()
    {
        if (!userManager.isSignedIn()) {
            savePaymentCheckbox.setVisibility(View.INVISIBLE);
            showSignInDialog();
        } else {
            try {
                CreditCard savedPaymentInfo = userManager.getCard(0);
                creditCardNum.setText(savedPaymentInfo.getCardNumber());
                expiryDate.setText(savedPaymentInfo.getExpiryDate());
                cvv.setText(savedPaymentInfo.getCvv());
                masterCard.setChecked(savedPaymentInfo.getType() == CardType.Mastercard);
                visa.setChecked(savedPaymentInfo.getType() == CardType.Visa);
                nameOfCardHolder.setText(savedPaymentInfo.getCardHolderName());
                savePaymentCheckbox.setVisibility(View.INVISIBLE);
                savePaymentCheckbox.setChecked(false);
            } catch (Exception ex) {
                savePaymentCheckbox.setVisibility(View.VISIBLE);
            }
        }
    }

    //If we are planning to use this popup in different areas, it may be best to create it's own class
    public void showSignInDialog() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.signin_popup_view, null);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;
        boolean focusable = true;
        popupWindow = new PopupWindow(popUpView, width, height, focusable);

        Button signInButton = popUpView.findViewById(R.id.enterButton);
        Button guestButton = popUpView.findViewById(R.id.guestButton);

        signInButton.setOnClickListener(v -> {
            Intent intent = new Intent(PagePayment.this, PageAccountSignIn.class);
            startActivityForResult(intent, REQUEST_CODE);
        });

        guestButton.setOnClickListener(v -> popupWindow.dismiss());

        layout.post(() -> popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                popupWindow.dismiss();
            }
        }
    }

    public void onGoBack(View view) {
        Intent intent = new Intent(PagePayment.this, PageCart.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void onProcessPayment(View view) {
        if (nameOfCardHolder.getText().toString().isEmpty() || creditCardNum.getText().toString().isEmpty()
        || expiryDate.getText().toString().isEmpty() || cvv.getText().toString().isEmpty()
        || (!masterCard.isChecked() && !visa.isChecked())){
            Toast.makeText(this, "Please fill out all fields before processing payment!", Toast.LENGTH_SHORT).show();
        } else {
            try{
                StoreHelper.runCreditCardPayment(
                        nameOfCardHolder.getText().toString(),
                        creditCardNum.getText().toString(),
                        expiryDate.getText().toString(),
                        cvv.getText().toString(),
                        masterCard.isChecked()
                );

                //Save card if box is checked.
                if (savePaymentCheckbox.isChecked())
                    userManager.saveCard
                            (
                                    nameOfCardHolder.getText().toString(),
                                    creditCardNum.getText().toString(),
                                    expiryDate.getText().toString(),
                                    cvv.getText().toString(),
                                    masterCard.isChecked()
                            );

                Toast.makeText(this, "Payment was successfully processed. Your order is on its way!", Toast.LENGTH_SHORT).show();
                StoreHelper.getCartManager().empty();
                onGoBack(null);
            } catch(UIException ex){
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Display order subtotal.
    private void displayTotal()
    {
        String subtotal = String.format("$%.2f", (cartManager.total()));
        subtotalText.setText(subtotal);
    }
}