package vn.edu.ntu.huuduc.kt592cnttnguyenphamhuuduc;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Calendar;

public class FirstFragment extends Fragment implements View.OnClickListener,
        AdapterView.OnItemSelectedListener {
    NavController navController;
    EditText edtNgay, edtMua, edtBan;
    ImageButton imgbNgay;
    RadioGroup rdgChon;
    RadioButton rdbTG, rdbSJC, rdbDOJI;
    Button btnThem, btnXem;
    Calendar calendar = Calendar.getInstance();

    public static final String sharePrefName = "my_share_preferense";
    public static final String key_str = "ten_str";

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navController = NavHostFragment.findNavController(this);
        ((MainActivity) getActivity()).navController = navController;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addViews();
        setViews();

    }

    private void setViews() {
        btnThem.setOnClickListener(this);
        btnXem.setOnClickListener(this);
        imgbNgay.setOnClickListener(this);
    }

    private void addViews() {
        edtNgay = ((MainActivity) getActivity()).findViewById(R.id.edtNgay);
        edtMua = ((MainActivity) getActivity()).findViewById(R.id.edtMua);
        edtBan = ((MainActivity) getActivity()).findViewById(R.id.edtBan);
        imgbNgay = ((MainActivity) getActivity()).findViewById(R.id.imgbNgay);
        rdgChon = ((MainActivity) getActivity()).findViewById(R.id.rdgChon);
        rdbTG = ((MainActivity) getActivity()).findViewById(R.id.rdbTG);
        rdbSJC = ((MainActivity) getActivity()).findViewById(R.id.rdbSJC);
        rdbDOJI = ((MainActivity) getActivity()).findViewById(R.id.rdbDOJI);
        btnXem = ((MainActivity) getActivity()).findViewById(R.id.btnXem);
        btnThem = ((MainActivity) getActivity()).findViewById(R.id.btnThem);
        setTextNgay(calendar);
    }

    private void setTextNgay(Calendar calendar) {
        StringBuilder builder = new StringBuilder();
        builder.append(calendar.get(Calendar.DAY_OF_MONTH)).append("/").
                append(calendar.get(Calendar.MONTH) + 1).append("/").
                append(calendar.get(Calendar.YEAR));
        edtNgay.setText(builder);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnThem:
                Them();
                break;
            case R.id.btnXem:
                Xem();
                break;
            case R.id.imgbNgay:
                ChonNgay();
                break;

        }
    }

    private void ChonNgay() {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dateOfMonth) {
                calendar.set(year, month, dateOfMonth);
                setTextNgay(calendar);
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(((MainActivity) getActivity()), listener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void Xem() {
        NavHostFragment.findNavController(this).navigate(R.id.action_FirstFragment_to_SecondFragment);
        SharedPreferences sharedPreferences = ((MainActivity) getActivity()).
                getSharedPreferences(sharePrefName, Context.MODE_PRIVATE);
    }

    private void Them() {
        NavHostFragment.findNavController(this).navigate(R.id.action_FirstFragment_to_SecondFragment);
        SharedPreferences sharedPreferences = ((MainActivity) getActivity()).
                getSharedPreferences(sharePrefName, Context.MODE_PRIVATE);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String str = String.format(
                    "%s\n" +
                            "%s\n" +
                            "Mua vào: %s" +
                            " Bán ra: %s\n",

                    edtNgay.getText().toString(),
                    hinhThucThanhToan(),
                    edtMua.getText().toString(),
                    edtBan.getText().toString()
            );
            editor.putString(key_str, str);
            editor.commit();
        }
    }

    private Object hinhThucThanhToan() {
        String str = "";
        if (rdbTG.isChecked()) {
            str = rdbTG.getText().toString();
        } else if (rdbSJC.isChecked()) {
            str = rdbSJC.getText().toString();
        } else if (rdbDOJI.isChecked()) {
            str = rdbDOJI.getText().toString();
        }
        return str;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}