package tdp.vip;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.craftman.cardform.CardForm;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Le quito la barra de notificaciones
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_payment);

        CardForm cardForm = (CardForm) findViewById(R.id.cardform);
        TextView txtAmountNumber = (TextView) findViewById(R.id.payment_amount);
        Button btnPay = (Button) findViewById(R.id.btn_pay);

        TextView txtCardName = (TextView) findViewById(R.id.card_name);
        TextView txtCardNameP = (TextView) findViewById(R.id.card_preview_name);

        TextView txtCardNumber = (TextView) findViewById(R.id.card_number);

        TextView txtCardExpiry = (TextView) findViewById(R.id.expiry_date);
        TextView txtCardExpiryP = (TextView) findViewById(R.id.card_preview_expiry);

        TextView txtAmount = (TextView) findViewById(R.id.payment_amount_holder);

        txtAmount.setText("Monto del pago:");
        txtAmountNumber.setText("$500.00");
        btnPay.setText("Pagar");

        txtCardName.setHint("Nombre");
        txtCardNameP.setHint("Nombre");

        txtCardNumber.setHint("Numero de tarjeta");

        txtCardExpiry.setHint("Fecha vencimiento");
        txtCardExpiryP.setHint("Fecha vencimiento");

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog progressDialog = new ProgressDialog(PaymentActivity.this);
                progressDialog.setTitle("Compra");
                progressDialog.setMessage("Validando pago...");
                progressDialog.show();

                Runnable progressRunnable = new Runnable() {

                @Override
                public void run() {
                        progressDialog.cancel();
                        Toast.makeText(PaymentActivity.this, "Pago exitoso!",
                            Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(PaymentActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                };

                Handler pdCanceller = new Handler();
                pdCanceller.postDelayed(progressRunnable, 4000);

            }
        });
    }
}
