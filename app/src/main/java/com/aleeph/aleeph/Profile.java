package com.aleeph.aleeph;

import android.animation.LayoutTransition;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import static com.aleeph.aleeph.R.id.input_training_end_date;
import static com.aleeph.aleeph.R.id.input_training_start_date;
import static com.aleeph.aleeph.R.id.iv_add_pg;
import static com.aleeph.aleeph.R.id.iv_add_phd;
import static com.aleeph.aleeph.R.id.iv_edit_personal_info;
import static com.aleeph.aleeph.R.id.ll_input_phd_details;
import static com.aleeph.aleeph.R.id.ll_pg_details;
import static com.aleeph.aleeph.R.id.skill_auto_complete_textview;
import static com.aleeph.aleeph.R.id.spinner_country_code;
import static com.aleeph.aleeph.R.id.spinner_title;
import static com.aleeph.aleeph.R.id.tv_add_education;
import static com.aleeph.aleeph.R.id.tv_add_family;
import static com.aleeph.aleeph.R.id.tv_add_jobs;
import static com.aleeph.aleeph.R.id.tv_add_skills;
import static com.aleeph.aleeph.R.id.tv_add_training;


public class Profile extends Fragment implements AdapterView.OnItemSelectedListener {

    private TextView tvAddEducation, tvAddSkills,tvAddFamily,tvAddTraining,tvAddJobs;
    private AutoCompleteTextView autoCompleteTextView;
    private EditText etTrainingStartDate,etTrainingEndDate;
    private ImageView ivEditPersonalInfo,ivAddPhd;
    private Spinner spinnerTitle,spinnerCountryCode;
    private ViewGroup viewGroup;
    LinearLayout llInputPhdDetails;
    DatePickerDialog datePickerDialog;

    Context context;
    String[] skills = {"android", "java", "php", "my SQL", "database engineer", "civil engineer", "mobile engineer", "automobile engineer", "mechanical engineer", "marine engineer", "diesel mechanic"};
    String[] title = {"Male", "Female", "Other"};
    String[] countryCode = {"+91","+01","+92",	"62", "98","964", "353","972","39"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        View v = inflater.inflate(R.layout.fragment_profile, null);
        tvAddEducation = v.findViewById(tv_add_education);
        tvAddFamily=v.findViewById(tv_add_family);
        tvAddSkills=v.findViewById(tv_add_skills);
        tvAddTraining=v.findViewById(tv_add_training);
        tvAddJobs=v.findViewById(tv_add_jobs);
        ivEditPersonalInfo = v.findViewById(iv_edit_personal_info);

        final LayoutTransition layoutTransition=new LayoutTransition();
        layoutTransition.enableTransitionType(LayoutTransition.CHANGING);



        context = getContext();

        tvAddSkills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.add_skills_dialog);
                dialog.setTitle("Skills:");

                autoCompleteTextView=dialog.findViewById(skill_auto_complete_textview);
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,skills);
                autoCompleteTextView.setThreshold(1);
                autoCompleteTextView.setAdapter(adapter);
                dialog.show();
            }
        });

        tvAddEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.add_education_dialog);
                dialog.setTitle("Education:");

                ivAddPhd=dialog.findViewById(iv_add_pg);
                llInputPhdDetails=dialog.findViewById(ll_input_phd_details);
                viewGroup=dialog.findViewById(ll_pg_details);
                viewGroup.setLayoutTransition(layoutTransition);
                ivAddPhd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(llInputPhdDetails.getParent()!=null) {
                            ((ViewGroup) llInputPhdDetails.getParent()).removeView(llInputPhdDetails);

                        }viewGroup.addView(llInputPhdDetails);
                        }
                });

                dialog.show();
            }
        });

        ivEditPersonalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.add_personal_information_dialog);
                dialog.setTitle("Personal Information:");

                spinnerTitle = dialog.findViewById(spinner_title);
//                spinnerTitle.setOnItemSelectedListener(dialog);
                ArrayAdapter titleArrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, title);
                titleArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerTitle.setAdapter(titleArrayAdapter);

                spinnerCountryCode=dialog.findViewById(spinner_country_code);
                ArrayAdapter countryArrayAdapter=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item, countryCode);
                countryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerTitle.setAdapter(titleArrayAdapter);
                dialog.show();

            }
        });

//        ivAddPhd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////
//            }
//        });



        tvAddFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.add_family_dialog);
                dialog.setTitle("Family Details:");
                dialog.show();
            }
        });

        tvAddTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                etTrainingStartDate=dialog.findViewById(input_training_start_date);
                etTrainingEndDate=dialog.findViewById(input_training_end_date);
                dialog.setContentView(R.layout.add_training_dialog);
                dialog.setTitle("Training Details:");
                dialog.show();

                etTrainingStartDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Calendar calendar=Calendar.getInstance();
                        int mYear = calendar.get(Calendar.YEAR); // current year
                        int mMonth = calendar.get(Calendar.MONTH); // current month
                        int mDay = calendar.get(Calendar.DAY_OF_MONTH);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                    String date=String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day);
                                    etTrainingStartDate.setText(date);
                                }
                            },mYear,mMonth,mDay);
                            datePickerDialog.show();
                        }
                    }
                });
                etTrainingEndDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Calendar calendar=Calendar.getInstance();
                        int mYear = calendar.get(Calendar.YEAR); // current year
                        int mMonth = calendar.get(Calendar.MONTH); // current month
                        int mDay = calendar.get(Calendar.DAY_OF_MONTH);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                    String date=String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day);
                                    etTrainingEndDate.setText(date);
                                }
                            },mYear,mMonth,mDay);
                            datePickerDialog.show();
                        }
                    }
                });

            }
        });

        tvAddJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.add_jobs_dialog);
                dialog.setTitle("Jobs Details:");
                dialog.show();
            }
        });

        return v;
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}