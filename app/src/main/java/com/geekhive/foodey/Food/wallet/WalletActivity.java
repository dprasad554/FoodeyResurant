package com.geekhive.foodey.Food.wallet;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geekhive.foodey.Food.beans.wallet.WalletBalance;
import com.geekhive.foodey.Food.utils.ConnectionDetector;
import com.geekhive.foodey.Food.utils.OnResponseListner;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Food.utils.SnackBar;
import com.geekhive.foodey.Food.utils.WebServices;
import com.geekhive.foodey.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletActivity extends AppCompatActivity implements OnResponseListner {

    @BindView(R.id.vR_t_list)
    RecyclerView vRTransactionlist;
    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.userEmail)
    TextView userEmail;
    @BindView(R.id.pointsUsr)
    TextView pointsUsr;

    TransactionAdapter transactionAdapter;
    ConnectionDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        ButterKnife.bind(this);
        setvalues();


    }

    private void setvalues() {
        mDetector = new ConnectionDetector(this);
        userEmail.setText(Prefs.getMobileNumber(this));
        userName.setText(Prefs.getUserName(this));
        transactionAdapter = new TransactionAdapter();
        vRTransactionlist.setLayoutManager(new LinearLayoutManager(this));
        vRTransactionlist.setAdapter(transactionAdapter);
        CallService();

    }

    private void CallService() {
        new WebServices(this).WalletBal(WebServices.Foodey_Services,
                WebServices.ApiType.walletbal, Prefs.getUserId(this));
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {

        if (apiType == WebServices.ApiType.walletbal) {
            if (!isSucces) {
                SnackBar.makeText(WalletActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                final WalletBalance walletBalance = (WalletBalance) response;

                if (!isSucces || walletBalance == null) {
                    SnackBar.makeText(WalletActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {
                    String amt = walletBalance.getUserWallet().getAmount() + " Points";
                    pointsUsr.setText(amt);
                }
            }
        }
    }

    public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {


        public TransactionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new TransactionAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_transaction, parent, false));
        }

        @Override
        public void onBindViewHolder(final TransactionAdapter.MyViewHolder holder, final int position) {

        }


        public int getItemCount() {
            return 4;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            public MyViewHolder(View view) {
                super(view);


            }
        }
    }
}
