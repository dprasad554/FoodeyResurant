// Generated code from Butter Knife. Do not modify!
package com.geekhive.foodey.Food.profile;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geekhive.foodey.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EditProfile_ViewBinding implements Unbinder {
  private EditProfile target;

  @UiThread
  public EditProfile_ViewBinding(EditProfile target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EditProfile_ViewBinding(EditProfile target, View source) {
    this.target = target;

    target.upload_profile_image = Utils.findRequiredViewAsType(source, R.id.upload_profile_image, "field 'upload_profile_image'", TextView.class);
    target.first_name = Utils.findRequiredViewAsType(source, R.id.first_name, "field 'first_name'", EditText.class);
    target.last_name = Utils.findRequiredViewAsType(source, R.id.last_name, "field 'last_name'", EditText.class);
    target.mobile_number = Utils.findRequiredViewAsType(source, R.id.mobile_number, "field 'mobile_number'", EditText.class);
    target.alternate_mobile = Utils.findRequiredViewAsType(source, R.id.alternate_mobile, "field 'alternate_mobile'", EditText.class);
    target.refer_code = Utils.findRequiredViewAsType(source, R.id.refer_code, "field 'refer_code'", EditText.class);
    target.vI_ep_back = Utils.findRequiredViewAsType(source, R.id.vI_ep_back, "field 'vI_ep_back'", ImageView.class);
    target.vT_af_header = Utils.findRequiredViewAsType(source, R.id.vT_af_header, "field 'vT_af_header'", TextView.class);
    target.update_profile = Utils.findRequiredViewAsType(source, R.id.update_profile, "field 'update_profile'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EditProfile target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.upload_profile_image = null;
    target.first_name = null;
    target.last_name = null;
    target.mobile_number = null;
    target.alternate_mobile = null;
    target.refer_code = null;
    target.vI_ep_back = null;
    target.vT_af_header = null;
    target.update_profile = null;
  }
}
