package com.example.android37_nguyen_dinh_thai_btvn_day05;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TimePicker;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class NoteScreen extends AppCompatActivity {
    Button btnDatePicker, btnTimePicker,btnTune;
    EditText txtDate, txtTime,txtTags,txtWeek;
    private int mYear, mMonth, mDay, mHour, mMinute;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_screen_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
    //respond to menu item selection
        switch (item.getItemId()) {
            case R.id.mnSave:
                //startActivity(new Intent(this, About.class));
                Toast.makeText(getBaseContext(),"Save",Toast.LENGTH_LONG).show();
                return true;
            case R.id.mnCancel:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_screen);
        setTitle(R.string.title2);
        //Declare tags

        final String[] type_str = new String[]{"Family","Game", "Android", "VTC","Friend" };
        final boolean[] checked_select = new boolean[]{ true, false,true,false,true};
        final List<String> itemlist = Arrays.asList(type_str);
        //Declare week
        final String[] date_str = new String[]{"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        final boolean[] checked_choose = new boolean[]{ true, true,false,false,false,true,false};
        final List<String> itemlist2 = Arrays.asList(date_str);
        //Declare: From defaults.
        final String[] from_defaults = new String[]{"Nexus Tune","WinPhone Tune","Peep Tune","Nokia Tune","Etc"};
        final boolean[] checked_defaults = new boolean[]{ false, false,true,false,false};
        final List<String> itemlist_defaults = Arrays.asList(from_defaults);
        txtTime= findViewById(R.id.txtTime);
        txtDate=findViewById(R.id.txtDate);
        btnDatePicker=(Button)findViewById(R.id.btnSetDate);
        btnTimePicker=(Button)findViewById(R.id.btnsetTime);
        btnTune=(Button)findViewById(R.id.btnTune);
        txtTags=findViewById(R.id.txtTag);
        txtWeek=findViewById(R.id.txtWeek);
        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(NoteScreen.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,int minute) {
                                txtTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(NoteScreen.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        //Tags
        txtTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(NoteScreen.this);
                builder.setMultiChoiceItems(type_str, checked_select, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                        // Update the current focused item's checked status
                        checked_select[which] = isChecked;
                        // Get the current focused item
                        String currentItem = itemlist.get(which);
                        Toast.makeText(getApplicationContext(),currentItem + " " + isChecked, Toast.LENGTH_SHORT).show();
                    }
                });
                // Set a title for alert dialog
                builder.setTitle("Choose tags:");
                // Set the positive/yes button click listener
                builder.setPositiveButton(R.string.ok_str, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i<checked_select.length; i++){
                            boolean checked = checked_select[i];
                            if (checked) {
                                Toast.makeText(getBaseContext(),txtTags.getText() + " + "+ itemlist.get(i),Toast.LENGTH_LONG).show();
                                txtTags.setText(txtTags.getText() + " + "+ itemlist.get(i));
                            }
                        }
                    }
                });
                // Set the negative/no button click listener
                builder.setNegativeButton(R.string.cancel_str, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when click the negative button
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        //Click week
        txtWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(NoteScreen.this);
                builder.setMultiChoiceItems(date_str, checked_choose, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                        // Update the current focused item's checked status
                        checked_choose[which] = isChecked;

                        // Get the current focused item
                        String currentItem = itemlist2.get(which);

                        // Notify the current action
                        Toast.makeText(getApplicationContext(),
                                currentItem + " " + isChecked, Toast.LENGTH_SHORT).show();
                    }
                });
                // Set a title for alert dialog
                builder.setTitle("Choose Date:");
                builder.setPositiveButton(R.string.ok_str, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i<checked_choose.length; i++){
                            boolean checked = checked_choose[i];
                            if (checked) {
                                Toast.makeText(getBaseContext(),txtWeek.getText() + " + "+ itemlist2.get(i),Toast.LENGTH_LONG).show();
                                txtWeek.setText(txtWeek.getText() + " + "+ itemlist2.get(i));
                            }
                        }
                    }
                });

                // Set the negative/no button click listener
                builder.setNegativeButton(R.string.cancel_str, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        //btnTune click
        btnTune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(NoteScreen.this, btnTune);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.fromfile:
                                Toast.makeText(getBaseContext(),"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.fromdefault: {
                                AlertDialog.Builder builder = new AlertDialog.Builder(NoteScreen.this);
                                builder.setMultiChoiceItems(from_defaults, checked_defaults, new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                                        // Update the current focused item's checked status
                                        checked_select[which] = isChecked;
                                        // Get the current focused item
                                        String currentItem = itemlist_defaults.get(which);
                                        Toast.makeText(getApplicationContext(),currentItem + " " + isChecked, Toast.LENGTH_SHORT).show();
                                    }
                                });
                                // Set a title for alert dialog
                                builder.setTitle("Choose Type Tune:");
                                // Set the positive/yes button click listener
                                builder.setPositiveButton(R.string.ok_str, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        for (int i = 0; i<checked_defaults.length; i++){
                                            boolean checked = checked_defaults[i];
                                            if (checked) {
                                                Toast.makeText(getBaseContext(), "->" + itemlist_defaults.get(i),Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }
                                });
                                // Set the negative/no button click listener
                                builder.setNegativeButton(R.string.cancel_str, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });

                                AlertDialog dialog = builder.create();
                                dialog.show();
                                return true;
                            }
                            default:
                                return false;
                        }

                    }
                });

                popup.show(); //showing popup menu
            }
        });
    }
}
