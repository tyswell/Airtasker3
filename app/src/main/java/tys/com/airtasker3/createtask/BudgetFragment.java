package tys.com.airtasker3.createtask;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import tys.com.airtasker3.R;
import tys.com.airtasker3.ui.NonSwipeViewPager;

/**
 * Created by chokechaic on 5/3/2016.
 */
public class BudgetFragment extends Fragment implements TextView.OnEditorActionListener {

    private NonSwipeViewPager nvp;
    private ToggleButton typeBudgetBtn;
    private TextView totalPriceView;
    private EditText totalPriceEdit;
    private LinearLayout hourlyRateLabelLayout;
    private EditText hourEdit;
    private EditText pricePerHourEdit;
    private TextView estimateText;
    private Button postATaskBtn;

    private boolean isTotalMode = false;

    public BudgetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_task_budget, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nvp = (NonSwipeViewPager) getActivity().findViewById(R.id.viewpagerX);

        typeBudgetBtn  = (ToggleButton) view.findViewById(R.id.typeBudgetBtn);
        typeBudgetBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                manageField(isChecked);
            }
        });

        totalPriceView = (TextView) view.findViewById(R.id.totalPriceView);
        totalPriceEdit = (EditText) view.findViewById(R.id.totalPriceEdit);
        totalPriceEdit.setOnEditorActionListener(this);

        hourlyRateLabelLayout = (LinearLayout) view.findViewById(R.id.hourlyRateLabelLayout);

        hourEdit = (EditText) view.findViewById(R.id.hourEdit);
        hourEdit.setOnEditorActionListener(this);

        pricePerHourEdit = (EditText) view.findViewById(R.id.pricePerHourEdit);
        pricePerHourEdit.setOnEditorActionListener(this);

        estimateText = (TextView) view.findViewById(R.id.estimateText);

        postATaskBtn = (Button) view.findViewById(R.id.postATaskBtn);
        postATaskBtn.setText(R.string.createtask_continue_label);
        postATaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nvp.getCurrentItem() == 2) {
                    Toast.makeText(getContext(), "End process", Toast.LENGTH_LONG).show();
                }
            }
        });

        manageField(false);
    }

    private void calcurateEstimateBudget() {
        int value = 0;
        try {
            if (isTotalMode) {
                value = Integer.parseInt(totalPriceEdit.getText().toString());
            }
            else {
                int hourValue = Integer.parseInt(hourEdit.getText().toString());
                int pricePerHourValue = Integer.parseInt(pricePerHourEdit.getText().toString());
                value = hourValue * pricePerHourValue;
            }

            estimateText.setText(""+value);
        } catch (Exception e) {
            estimateText.setText(null);
        }
    }

    private void manageField(boolean isHourlyRate) {

        if (isHourlyRate) {
            //HOURLY RATE
            hourlyRateLabelLayout.setVisibility(View.VISIBLE);

            totalPriceView.setVisibility(View.GONE);
            totalPriceEdit.setVisibility(View.GONE);
            totalPriceEdit.setText(null);

            isTotalMode = false;
        } else {
            //TOTAL
            hourlyRateLabelLayout.setVisibility(View.GONE);
            hourEdit.setText(null);
            pricePerHourEdit.setText(null);

            totalPriceView.setVisibility(View.VISIBLE);
            totalPriceEdit.setVisibility(View.VISIBLE);

            isTotalMode = true;
        }

        estimateText.setText(null);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                actionId == EditorInfo.IME_ACTION_DONE ||
                event.getAction() == KeyEvent.ACTION_DOWN &&
                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
            calcurateEstimateBudget();
            return false; // consume.
        }
        return false; // pass on to other listeners.
    }
}
