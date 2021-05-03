// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.tablebooking;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TableBookingActivity_ViewBinding implements Unbinder {
  private TableBookingActivity target;

  @UiThread
  public TableBookingActivity_ViewBinding(TableBookingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TableBookingActivity_ViewBinding(TableBookingActivity target, View source) {
    this.target = target;

    target.et_DBName = Utils.findRequiredViewAsType(source, R.id.et_DBName, "field 'et_DBName'", EditText.class);
    target.et_DBMobile = Utils.findRequiredViewAsType(source, R.id.et_DBMobile, "field 'et_DBMobile'", EditText.class);
    target.alt_DBMobile = Utils.findRequiredViewAsType(source, R.id.alt_DBMobile, "field 'alt_DBMobile'", EditText.class);
    target.et_DBGuests = Utils.findRequiredViewAsType(source, R.id.et_DBGuests, "field 'et_DBGuests'", EditText.class);
    target.et_DBEmail = Utils.findRequiredViewAsType(source, R.id.et_DBEmail, "field 'et_DBEmail'", EditText.class);
    target.tv_DBDate = Utils.findRequiredViewAsType(source, R.id.tv_DBDate, "field 'tv_DBDate'", TextView.class);
    target.tv_DBTime = Utils.findRequiredViewAsType(source, R.id.tv_DBTime, "field 'tv_DBTime'", TextView.class);
    target.dateLinear = Utils.findRequiredViewAsType(source, R.id.dateLinear, "field 'dateLinear'", LinearLayout.class);
    target.timeLinear = Utils.findRequiredViewAsType(source, R.id.timeLinear, "field 'timeLinear'", LinearLayout.class);
    target.vI_aac_table_back = Utils.findRequiredViewAsType(source, R.id.vI_aac_table_back, "field 'vI_aac_table_back'", ImageView.class);
    target.bookTable = Utils.findRequiredViewAsType(source, R.id.bookTable, "field 'bookTable'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TableBookingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.et_DBName = null;
    target.et_DBMobile = null;
    target.alt_DBMobile = null;
    target.et_DBGuests = null;
    target.et_DBEmail = null;
    target.tv_DBDate = null;
    target.tv_DBTime = null;
    target.dateLinear = null;
    target.timeLinear = null;
    target.vI_aac_table_back = null;
    target.bookTable = null;
  }
}
