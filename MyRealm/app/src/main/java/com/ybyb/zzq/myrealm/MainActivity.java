package com.ybyb.zzq.myrealm;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.ybyb.zzq.myrealm.data.SimpleClass;
import com.ybyb.zzq.myrealm.data.Student;
import com.ybyb.zzq.myrealm.data.Subject;
import com.ybyb.zzq.myrealm.result.MyRvAdapter;
import com.ybyb.zzq.myrealm.result.QueryResult;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mTestAddBtn1;
    private Button mTestAddBtn2;
    private Button mTestQueryBtn1;
    private Button mTestQueryBtn2;
    private Button mTestDeleteBtn;
    private RecyclerView mQueryRlv;
    private TextView mClassTv;
    private TextView mQueryTv;
    private List<TextView> mTvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.bindViews();
        this.addListeners();
    }

    /**
     * 添加监听
     */
    private void addListeners() {
        this.mTestAddBtn1.setOnClickListener(this);
        this.mTestAddBtn2.setOnClickListener(this);
        this.mTestQueryBtn1.setOnClickListener(this);
        this.mTestQueryBtn2.setOnClickListener(this);
        this.mTestDeleteBtn.setOnClickListener(this);
    }
    /**
     * 绑定控件
     */
    private void bindViews() {
        this.mTestAddBtn1 = (Button) findViewById(R.id.test_btn1);
        this.mTestAddBtn2 = (Button) findViewById(R.id.test_btn2);
        this.mTestQueryBtn1 = (Button) findViewById(R.id.test_btn3);
        this.mTestQueryBtn2 = (Button) findViewById(R.id.test_btn4);
        this.mTestDeleteBtn = (Button) findViewById(R.id.test_btn5);
        this.mQueryRlv = (RecyclerView) findViewById(R.id.query_rlv);
        this.mQueryTv = (TextView) findViewById(R.id.query_tv);
        this.mClassTv = (TextView) findViewById(R.id.test_class_name1);
        this.mTvList = new ArrayList<>();
        this.mTvList.add((TextView) findViewById(R.id.number_tv1));
        this.mTvList.add((TextView) findViewById(R.id.name_tv1));
        this.mTvList.add((TextView) findViewById(R.id.gender_tv1));
        this.mTvList.add((TextView) findViewById(R.id.paiming_tv1));
        this.mTvList.add((TextView) findViewById(R.id.sub_a_tv1));
        this.mTvList.add((TextView) findViewById(R.id.sub__a_score_tv1));
        this.mTvList.add((TextView) findViewById(R.id.sub_b_tv1));
        this.mTvList.add((TextView) findViewById(R.id.sub_b_score_tv1));
        this.mTvList.add((TextView) findViewById(R.id.sub_c_tv1));
        this.mTvList.add((TextView) findViewById(R.id.sub_c_score_tv1));
        this.mTvList.add((TextView) findViewById(R.id.sub_d_tv1));
        this.mTvList.add((TextView) findViewById(R.id.sub_d_score_tv1));
        this.mTvList.add((TextView) findViewById(R.id.total_tv1));
        this.mTvList.add((TextView) findViewById(R.id.average_tv1));

        this.mTvList.add((TextView) findViewById(R.id.number_tv2));
        this.mTvList.add((TextView) findViewById(R.id.name_tv2));
        this.mTvList.add((TextView) findViewById(R.id.gender_tv2));
        this.mTvList.add((TextView) findViewById(R.id.paiming_tv2));
        this.mTvList.add((TextView) findViewById(R.id.sub_a_tv2));
        this.mTvList.add((TextView) findViewById(R.id.sub__a_score_tv2));
        this.mTvList.add((TextView) findViewById(R.id.sub_b_tv2));
        this.mTvList.add((TextView) findViewById(R.id.sub_b_score_tv2));
        this.mTvList.add((TextView) findViewById(R.id.sub_c_tv2));
        this.mTvList.add((TextView) findViewById(R.id.sub_c_score_tv2));
        this.mTvList.add((TextView) findViewById(R.id.sub_d_tv2));
        this.mTvList.add((TextView) findViewById(R.id.sub_d_score_tv2));
        this.mTvList.add((TextView) findViewById(R.id.total_tv2));
        this.mTvList.add((TextView) findViewById(R.id.average_tv2));

        this.mTvList.add((TextView) findViewById(R.id.number_tv3));
        this.mTvList.add((TextView) findViewById(R.id.name_tv3));
        this.mTvList.add((TextView) findViewById(R.id.gender_tv3));
        this.mTvList.add((TextView) findViewById(R.id.paiming_tv3));
        this.mTvList.add((TextView) findViewById(R.id.sub_a_tv3));
        this.mTvList.add((TextView) findViewById(R.id.sub__a_score_tv3));
        this.mTvList.add((TextView) findViewById(R.id.sub_b_tv3));
        this.mTvList.add((TextView) findViewById(R.id.sub_b_score_tv3));
        this.mTvList.add((TextView) findViewById(R.id.sub_c_tv3));
        this.mTvList.add((TextView) findViewById(R.id.sub_c_score_tv3));
        this.mTvList.add((TextView) findViewById(R.id.sub_d_tv3));
        this.mTvList.add((TextView) findViewById(R.id.sub_d_score_tv3));
        this.mTvList.add((TextView) findViewById(R.id.total_tv3));
        this.mTvList.add((TextView) findViewById(R.id.average_tv3));

        this.mTvList.add((TextView) findViewById(R.id.number_tv4));
        this.mTvList.add((TextView) findViewById(R.id.name_tv4));
        this.mTvList.add((TextView) findViewById(R.id.gender_tv4));
        this.mTvList.add((TextView) findViewById(R.id.paiming_tv4));
        this.mTvList.add((TextView) findViewById(R.id.sub_a_tv4));
        this.mTvList.add((TextView) findViewById(R.id.sub__a_score_tv4));
        this.mTvList.add((TextView) findViewById(R.id.sub_b_tv4));
        this.mTvList.add((TextView) findViewById(R.id.sub_b_score_tv4));
        this.mTvList.add((TextView) findViewById(R.id.sub_c_tv4));
        this.mTvList.add((TextView) findViewById(R.id.sub_c_score_tv4));
        this.mTvList.add((TextView) findViewById(R.id.sub_d_tv4));
        this.mTvList.add((TextView) findViewById(R.id.sub_d_score_tv4));
        this.mTvList.add((TextView) findViewById(R.id.total_tv4));
        this.mTvList.add((TextView) findViewById(R.id.average_tv4));

        this.mTvList.add((TextView) findViewById(R.id.number_tv5));
        this.mTvList.add((TextView) findViewById(R.id.name_tv5));
        this.mTvList.add((TextView) findViewById(R.id.gender_tv5));
        this.mTvList.add((TextView) findViewById(R.id.paiming_tv5));
        this.mTvList.add((TextView) findViewById(R.id.sub_a_tv5));
        this.mTvList.add((TextView) findViewById(R.id.sub__a_score_tv5));
        this.mTvList.add((TextView) findViewById(R.id.sub_b_tv5));
        this.mTvList.add((TextView) findViewById(R.id.sub_b_score_tv5));
        this.mTvList.add((TextView) findViewById(R.id.sub_c_tv5));
        this.mTvList.add((TextView) findViewById(R.id.sub_c_score_tv5));
        this.mTvList.add((TextView) findViewById(R.id.sub_d_tv5));
        this.mTvList.add((TextView) findViewById(R.id.sub_d_score_tv5));
        this.mTvList.add((TextView) findViewById(R.id.total_tv5));
        this.mTvList.add((TextView) findViewById(R.id.average_tv5));

        this.mTvList.add((TextView) findViewById(R.id.number_tv6));
        this.mTvList.add((TextView) findViewById(R.id.name_tv6));
        this.mTvList.add((TextView) findViewById(R.id.gender_tv6));
        this.mTvList.add((TextView) findViewById(R.id.paiming_tv6));
        this.mTvList.add((TextView) findViewById(R.id.sub_a_tv6));
        this.mTvList.add((TextView) findViewById(R.id.sub__a_score_tv6));
        this.mTvList.add((TextView) findViewById(R.id.sub_b_tv6));
        this.mTvList.add((TextView) findViewById(R.id.sub_b_score_tv6));
        this.mTvList.add((TextView) findViewById(R.id.sub_c_tv6));
        this.mTvList.add((TextView) findViewById(R.id.sub_c_score_tv6));
        this.mTvList.add((TextView) findViewById(R.id.sub_d_tv6));
        this.mTvList.add((TextView) findViewById(R.id.sub_d_score_tv6));
        this.mTvList.add((TextView) findViewById(R.id.total_tv6));
        this.mTvList.add((TextView) findViewById(R.id.average_tv6));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test_btn1:
                writeDataIntoRealm();
                updateView();
                break;
            case R.id.test_btn2:
                View view = getLayoutInflater().inflate(R.layout.dialog_insert_data, null);//设置绑定手机号的弹出式对话框
                final AlertDialog insertDataDialog = new AlertDialog.Builder(this).create();
                insertDataDialog.setView(new EditText(this));//设置一个空的EditText当view中的EditText获取焦点后让键盘强制弹出
                insertDataDialog.show();
                insertDataDialog.getWindow().setContentView(view);
                insertDataDialog.setCanceledOnTouchOutside(true);
                final EditText idEt = (EditText) view.findViewById(R.id.id_et);
                final EditText nameEt = (EditText) view.findViewById(R.id.name_et);
                final EditText genderEt = (EditText) view.findViewById(R.id.gender_et);
                final EditText ageEt = (EditText) view.findViewById(R.id.age_et);
                final EditText scoreEt1 = (EditText) view.findViewById(R.id.score_et_1);
                final EditText scoreEt2 = (EditText) view.findViewById(R.id.score_et_2);
                final EditText scoreEt3 = (EditText) view.findViewById(R.id.score_et_3);
                final EditText subjectEt = (EditText) view.findViewById(R.id.subject_et);
                final EditText scoreEt4 = (EditText) view.findViewById(R.id.score_et_4);
                Button cancelBtn = (Button) view.findViewById(R.id.cancel_btn);
                Button confirmBtn = (Button) view.findViewById(R.id.confirm_btn);
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        insertDataDialog.dismiss();
                    }
                });
                confirmBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!idEt.getText().toString().equals("")
                            && !nameEt.getText().toString().equals("")
                            && !genderEt.getText().toString().equals("")
                            && !ageEt.getText()
                                     .toString()
                                     .equals("")
                            && !scoreEt1.getText().toString().equals("")
                            && !scoreEt2.getText().toString().equals("")
                            && !scoreEt3.getText().toString().equals("")
                            && !subjectEt.getText().toString().equals("")
                            && !scoreEt4.getText().toString().equals("")) {
                            List<String> data = new ArrayList<>();
                            data.add(idEt.getText().toString());
                            data.add(nameEt.getText().toString());
                            data.add(genderEt.getText().toString());
                            data.add(ageEt.getText().toString());
                            data.add(scoreEt1.getText().toString());
                            data.add(scoreEt2.getText().toString());
                            data.add(scoreEt3.getText().toString());
                            data.add(subjectEt.getText().toString());
                            data.add(scoreEt4.getText().toString());
                            insertDataDialog.dismiss();
                            specialInsertData(data);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "信息输入不完整！", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                break;
            case R.id.test_btn3:
                this.mQueryTv.setVisibility(View.VISIBLE);
                final int[] option = { 0 };
                new AlertDialog.Builder(this).setTitle("查询条件")
                                             .setIcon(android.R.drawable.ic_dialog_info)
                                             .setSingleChoiceItems(new String[] { "排名第一", "排名前三", "排名后三", "排名最后" }, 0, new DialogInterface.OnClickListener() {
                                                 public void onClick(DialogInterface dialog, int which) {
                                                     option[0] = which;
                                                 }
                                             })
                                             .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                 @Override
                                                 public void onClick(DialogInterface dialog, int which) {
                                                     dialog.dismiss();
                                                     queryDataFromRealm(option[0]);
                                                 }
                                             })
                                             .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                                 public void onClick(DialogInterface dialog, int which) {
                                                     dialog.dismiss();
                                                 }
                                             })
                                             .show();
                break;
            case R.id.test_btn4:
                this.mQueryTv.setVisibility(View.VISIBLE);
                testInQueryFromRealm();
                break;
            case R.id.test_btn5:
                this.mQueryTv.setVisibility(View.VISIBLE);
                clearDataFromRealm();
                break;
        }
    }

    /**
     * 清理错误数据
     */
    private void clearDataFromRealm() {
        final Realm realm=Realm.getDefaultInstance();
        final RealmResults<Student> results = realm.where(Student.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                List<Student> temp=new ArrayList<Student>();
                List<Student> repeat=new ArrayList<Student>();
                for (Student s:results){
                    if (!temp.contains(s)) {
                        temp.add(s);
                    }else {
                        repeat.add(s);
                    }
                }
                for (Student s:repeat){
                    s.deleteFromRealm();
                }
                queryDataFromRealm(4);
            }
        });

    }

    /**
     * 根据用户输入添加数据
     * @param data
     */
    private void specialInsertData(List<String> data) {
        String id = data.get(0);
        String name = data.get(1);
        String gender = data.get(2);
        String age = data.get(3);
        String chinese = data.get(4);
        String math = data.get(5);
        String english = data.get(6);
        String sub = data.get(7);
        String score = data.get(8);
        List<Integer> ids = new ArrayList<>();
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Student> students = realm.where(Student.class).findAll();
        for (Student s : students) {
            ids.add(Integer.valueOf(s.getId() + ""));
        }
        boolean isRight = true;
        int nowId = -1;
        int nowAge = -1;
        try {
            nowId = Integer.valueOf(id);
            nowAge = Integer.valueOf(age);
        } catch (Exception e) {
            isRight = false;
        }
        if (nowId != -1) {
            if (ids.contains(nowId)) {
                isRight = false;
            }
        }
        if (!gender.equals("男") && !gender.equals("女")) {
            isRight = false;
        }
        if (nowAge != -1) {
            if (nowAge < 10 || nowAge > 20) {
                isRight = false;
            }
        }
        if (!sub.equals("理综") && !sub.equals("文综")) {
            isRight = false;
        }
        if (Double.valueOf(chinese) > 150
            || Double.valueOf(chinese) < 0
            || Double.valueOf(math) > 150
            || Double.valueOf(math) < 0
            || Double.valueOf(english) > 150
            || Double.valueOf(english) < 0
            || Double.valueOf(score) > 300
            || Double.valueOf(score) < 0) {
            isRight = false;
        }
        if (!isRight) {
            Toast.makeText(this, "数据输入错误，请重新输入！", Toast.LENGTH_LONG).show();
        }
        else {
            RealmResults<SimpleClass> classes = realm.where(SimpleClass.class).findAll();
            realm.beginTransaction();
            SimpleClass nowClass = classes.get(0);
            Student student = realm.createObject(Student.class);
            student.setId(nowId);
            student.setName(name);
            student.setGender(gender);
            Subject subject1 = new Subject();
            subject1.setName("语文");
            subject1.setScore(Double.valueOf(chinese));
            Subject subject2 = new Subject();
            subject2.setName("数学");
            subject2.setScore(Double.valueOf(math));
            Subject subject3 = new Subject();
            subject3.setName("英语");
            subject3.setScore(Double.valueOf(english));
            Subject subject4 = new Subject();
            subject4.setName(sub);
            subject4.setScore(Double.valueOf(score));
            student.getSubjects().add(subject1);
            student.getSubjects().add(subject2);
            student.getSubjects().add(subject3);
            student.getSubjects().add(subject4);
            student.setTotalScore(getTotalScore(student.getSubjects()));
            student.setAverageScore(student.getTotalScore() / student.getSubjects().size());
            nowClass.getStudents().add(student);
            setOrder(nowClass.getStudents());
            realm.commitTransaction();
            Toast.makeText(this, "插入数据成功！", Toast.LENGTH_LONG).show();
            this.mQueryTv.setVisibility(View.VISIBLE);
            queryDataFromRealm(4);
        }
    }

    /**
     * 使用in（）方法查询数据
     */
    private void testInQueryFromRealm() {
        Realm realm = Realm.getDefaultInstance();
        List<QueryResult> result = new ArrayList<>();
        RealmResults<Student> student = realm.where(Student.class).in("name", new String[] { "张三", "吕布", "关羽", "孔明" }).findAllSorted("totalScore", Sort.DESCENDING);
        for (Student s : student) {
            QueryResult res = new QueryResult();
            res.setId(s.getId());
            res.setName(s.getName());
            res.setGender(s.getGender());
            res.setOrder(s.getOrderScore());
            res.setSubject1(s.getSubjects().get(0).getName());
            res.setScore1(s.getSubjects().get(0).getScore());
            res.setSubject2(s.getSubjects().get(1).getName());
            res.setScore2(s.getSubjects().get(1).getScore());
            res.setSubject3(s.getSubjects().get(2).getName());
            res.setScore3(s.getSubjects().get(2).getScore());
            res.setSubject4(s.getSubjects().get(3).getName());
            res.setScore4(s.getSubjects().get(3).getScore());
            res.setTotal(s.getTotalScore());
            res.setAverage(s.getAverageScore());
            result.add(res);
        }
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        this.mQueryRlv.setLayoutManager(manager);
        MyRvAdapter adapter = new MyRvAdapter(this, result);
        this.mQueryRlv.setAdapter(adapter);
    }

    /**
     * 根据不同条件进行查询
     * @param option
     */
    private void queryDataFromRealm(int option) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Student> student = realm.where(Student.class).findAll();
        List<QueryResult> result = null;
        switch (option) {
            case 0:
                result = new ArrayList<>();
                RealmResults<Student> student1 = realm.where(Student.class).equalTo("orderScore", 1).findAll();
                QueryResult res = new QueryResult();
                res.setId(student1.get(0).getId());
                res.setName(student1.get(0).getName());
                res.setGender(student1.get(0).getGender());
                res.setOrder(student1.get(0).getOrderScore());
                res.setSubject1(student1.get(0).getSubjects().get(0).getName());
                res.setScore1(student1.get(0).getSubjects().get(0).getScore());
                res.setSubject2(student1.get(0).getSubjects().get(1).getName());
                res.setScore2(student1.get(0).getSubjects().get(1).getScore());
                res.setSubject3(student1.get(0).getSubjects().get(2).getName());
                res.setScore3(student1.get(0).getSubjects().get(2).getScore());
                res.setSubject4(student1.get(0).getSubjects().get(3).getName());
                res.setScore4(student1.get(0).getSubjects().get(3).getScore());
                res.setTotal(student1.get(0).getTotalScore());
                res.setAverage(student1.get(0).getAverageScore());
                result.add(res);
                break;
            case 1:
                result = new ArrayList<>();
                RealmResults<Student> student2 = realm.where(Student.class).lessThanOrEqualTo("orderScore", 3).findAll();
                for (Student stu : student2) {
                    QueryResult res1 = new QueryResult();
                    res1.setId(stu.getId());
                    res1.setName(stu.getName());
                    res1.setGender(stu.getGender());
                    res1.setOrder(stu.getOrderScore());
                    res1.setSubject1(stu.getSubjects().get(0).getName());
                    res1.setScore1(stu.getSubjects().get(0).getScore());
                    res1.setSubject2(stu.getSubjects().get(1).getName());
                    res1.setScore2(stu.getSubjects().get(1).getScore());
                    res1.setSubject3(stu.getSubjects().get(2).getName());
                    res1.setScore3(stu.getSubjects().get(2).getScore());
                    res1.setSubject4(stu.getSubjects().get(3).getName());
                    res1.setScore4(stu.getSubjects().get(3).getScore());
                    res1.setTotal(stu.getTotalScore());
                    res1.setAverage(stu.getAverageScore());
                    result.add(res1);
                }
                break;
            case 2:
                result = new ArrayList<>();
                RealmResults<Student> student3 = realm.where(Student.class).greaterThanOrEqualTo("orderScore", student.size() - 2).findAll();
                for (Student stu : student3) {
                    QueryResult res1 = new QueryResult();
                    res1.setId(stu.getId());
                    res1.setName(stu.getName());
                    res1.setGender(stu.getGender());
                    res1.setOrder(stu.getOrderScore());
                    res1.setSubject1(stu.getSubjects().get(0).getName());
                    res1.setScore1(stu.getSubjects().get(0).getScore());
                    res1.setSubject2(stu.getSubjects().get(1).getName());
                    res1.setScore2(stu.getSubjects().get(1).getScore());
                    res1.setSubject3(stu.getSubjects().get(2).getName());
                    res1.setScore3(stu.getSubjects().get(2).getScore());
                    res1.setSubject4(stu.getSubjects().get(3).getName());
                    res1.setScore4(stu.getSubjects().get(3).getScore());
                    res1.setTotal(stu.getTotalScore());
                    res1.setAverage(stu.getAverageScore());
                    result.add(res1);
                }
                break;
            case 3:
                result = new ArrayList<>();
                RealmResults<Student> student4 = realm.where(Student.class).equalTo("orderScore", student.size()).findAll();
                QueryResult res3 = new QueryResult();
                res3.setId(student4.get(0).getId());
                res3.setName(student4.get(0).getName());
                res3.setGender(student4.get(0).getGender());
                res3.setOrder(student4.get(0).getOrderScore());
                res3.setSubject1(student4.get(0).getSubjects().get(0).getName());
                res3.setScore1(student4.get(0).getSubjects().get(0).getScore());
                res3.setSubject2(student4.get(0).getSubjects().get(1).getName());
                res3.setScore2(student4.get(0).getSubjects().get(1).getScore());
                res3.setSubject3(student4.get(0).getSubjects().get(2).getName());
                res3.setScore3(student4.get(0).getSubjects().get(2).getScore());
                res3.setSubject4(student4.get(0).getSubjects().get(3).getName());
                res3.setScore4(student4.get(0).getSubjects().get(3).getScore());
                res3.setTotal(student4.get(0).getTotalScore());
                res3.setAverage(student4.get(0).getAverageScore());
                result.add(res3);
                break;
            case 4:
                result = new ArrayList<>();
                RealmResults<Student> student5 = realm.where(Student.class).findAllSorted("totalScore",Sort.DESCENDING);
                for (Student stu : student5) {
                    QueryResult res1 = new QueryResult();
                    res1.setId(stu.getId());
                    res1.setName(stu.getName());
                    res1.setGender(stu.getGender());
                    res1.setOrder(stu.getOrderScore());
                    res1.setSubject1(stu.getSubjects().get(0).getName());
                    res1.setScore1(stu.getSubjects().get(0).getScore());
                    res1.setSubject2(stu.getSubjects().get(1).getName());
                    res1.setScore2(stu.getSubjects().get(1).getScore());
                    res1.setSubject3(stu.getSubjects().get(2).getName());
                    res1.setScore3(stu.getSubjects().get(2).getScore());
                    res1.setSubject4(stu.getSubjects().get(3).getName());
                    res1.setScore4(stu.getSubjects().get(3).getScore());
                    res1.setTotal(stu.getTotalScore());
                    res1.setAverage(stu.getAverageScore());
                    result.add(res1);
                }
                break;
        }
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        this.mQueryRlv.setLayoutManager(manager);
        MyRvAdapter adapter = new MyRvAdapter(this, result);
        this.mQueryRlv.setAdapter(adapter);
    }
    /**
     * 更新页面显示
     */
    private void updateView() {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<SimpleClass> result = mRealm.where(SimpleClass.class).findAll();
        this.mClassTv.setText(result.get(0).getClassName());
        for (int i = 0; i < this.mTvList.size(); i++) {
            int j = i / 14;
            switch (i % 14) {
                case 0:
                    this.mTvList.get(i).setText(result.get(0).getStudents().get(j).getId() + "");
                    break;
                case 1:
                    this.mTvList.get(i).setText(result.get(0).getStudents().get(j).getName() + "");
                    break;
                case 2:
                    this.mTvList.get(i).setText(result.get(0).getStudents().get(j).getGender() + "");
                    break;
                case 3:
                    RealmList<Subject> score1 = result.get(0).getStudents().get(j).getSubjects();
                    RealmList<Student> students = result.get(0).getStudents();
                    this.mTvList.get(i).setText(getOrder(score1, students) + "");
                    break;
                case 4:
                    this.mTvList.get(i).setText(result.get(0).getStudents().get(j).getSubjects().get(0).getName() + "");
                    break;
                case 5:
                    this.mTvList.get(i).setText(result.get(0).getStudents().get(j).getSubjects().get(0).getScore() + "");
                    break;
                case 6:
                    this.mTvList.get(i).setText(result.get(0).getStudents().get(j).getSubjects().get(1).getName() + "");
                    break;
                case 7:
                    this.mTvList.get(i).setText(result.get(0).getStudents().get(j).getSubjects().get(1).getScore() + "");
                    break;
                case 8:
                    this.mTvList.get(i).setText(result.get(0).getStudents().get(j).getSubjects().get(2).getName() + "");
                    break;
                case 9:
                    this.mTvList.get(i).setText(result.get(0).getStudents().get(j).getSubjects().get(2).getScore() + "");
                    break;
                case 10:
                    this.mTvList.get(i).setText(result.get(0).getStudents().get(j).getSubjects().get(3).getName() + "");
                    break;
                case 11:
                    this.mTvList.get(i).setText(result.get(0).getStudents().get(j).getSubjects().get(3).getScore() + "");
                    break;
                case 12:
                    RealmList<Subject> score2 = result.get(0).getStudents().get(j).getSubjects();
                    this.mTvList.get(i).setText(getTotalScore(score2) + "");
                    break;
                case 13:
                    RealmList<Subject> score3 = result.get(0).getStudents().get(j).getSubjects();
                    this.mTvList.get(i).setText(getAverageScore(score3) + "");
                    break;
            }
        }
    }
    /**
     * 计算平均分
     * @param score3
     * @return
     */
    private String getAverageScore(RealmList<Subject> score3) {
        double result = 0;
        double sum = 0;
        for (Subject sub : score3) {
            sum += sub.getScore();
        }
        result = sum / score3.size();
        return doubleToString(result);
    }

    /**
     * 计算总分
     * @param score2
     * @return
     */
    private double getTotalScore(RealmList<Subject> score2) {
        double sum = 0;
        for (Subject sub : score2) {
            sum += sub.getScore();
        }
        return sum;
    }

    /**
     * 拿到某一个学生对象的排名结果
     * @param score1
     * @param students
     * @return
     */
    private String getOrder(RealmList<Subject> score1, RealmList<Student> students) {
        double nowScore = getTotalScore(score1);
        List<Double> scores = new ArrayList<>();
        for (Student s : students) {
            double temp = getTotalScore(s.getSubjects());
            if (temp != nowScore) {
                scores.add(temp);
            }
        }
        int result = 1;
        for (int i = 0; i < scores.size(); i++) {
            if (nowScore < scores.get(i)) {
                result++;
            }
        }
        return result + "";
    }

    /**
     * 对数据进行排序
     * @param students
     */
    private void setOrder(RealmList<Student> students) {
        for (Student student : students) {
            RealmList<Subject> score = student.getSubjects();
            String order = getOrder(score, students);
            student.setOrderScore(Integer.valueOf(order));
        }
    }

    /**
     * 生成固定的数据并添加到Realm数据库中去
     *
     */
    private void writeDataIntoRealm() {
        Realm mRealm = Realm.getDefaultInstance();
        mRealm.beginTransaction();
        SimpleClass simpleClass = mRealm.createObject(SimpleClass.class);
        simpleClass.setClassName("人才中学高三一班");
        Student stu1 = mRealm.createObject(Student.class);
        stu1.setId(2);
        stu1.setAge(18);
        stu1.setGender("男");
        stu1.setName("吕布");

        Subject sub1 = mRealm.createObject(Subject.class);
        sub1.setName("语文");
        sub1.setFullScore(150);
        sub1.setScore(125.5);
        Subject sub2 = mRealm.createObject(Subject.class);
        sub2.setName("数学");
        sub2.setFullScore(150);
        sub2.setScore(100);
        Subject sub3 = mRealm.createObject(Subject.class);
        sub3.setName("英语");
        sub3.setFullScore(150);
        sub3.setScore(121);
        Subject sub4 = mRealm.createObject(Subject.class);
        sub4.setName("理综");
        sub4.setFullScore(300);
        sub4.setScore(246);

        stu1.getSubjects().add(sub1);
        stu1.getSubjects().add(sub2);
        stu1.getSubjects().add(sub3);
        stu1.getSubjects().add(sub4);
        stu1.setTotalScore(getTotalScore(stu1.getSubjects()));
        stu1.setAverageScore(stu1.getTotalScore() / stu1.getSubjects().size());
        Student stu2 = mRealm.createObject(Student.class);
        stu2.setId(1);
        stu2.setAge(17);
        stu2.setGender("女");
        stu2.setName("貂蝉");

        Subject sub5 = mRealm.createObject(Subject.class);
        sub5.setName("语文");
        sub5.setFullScore(150);
        sub5.setScore(146.5);
        Subject sub6 = mRealm.createObject(Subject.class);
        sub6.setName("数学");
        sub6.setFullScore(150);
        sub6.setScore(90);
        Subject sub7 = mRealm.createObject(Subject.class);
        sub7.setName("英语");
        sub7.setFullScore(150);
        sub7.setScore(141);
        Subject sub8 = mRealm.createObject(Subject.class);
        sub8.setName("文综");
        sub8.setFullScore(300);
        sub8.setScore(235);

        stu2.getSubjects().add(sub5);
        stu2.getSubjects().add(sub6);
        stu2.getSubjects().add(sub7);
        stu2.getSubjects().add(sub8);
        stu2.setTotalScore(getTotalScore(stu2.getSubjects()));
        stu2.setAverageScore(stu2.getTotalScore() / stu2.getSubjects().size());
        Student stu3 = mRealm.createObject(Student.class);
        stu3.setId(4);
        stu3.setAge(16);
        stu3.setGender("女");
        stu3.setName("小乔");

        Subject sub9 = mRealm.createObject(Subject.class);
        sub9.setName("语文");
        sub9.setFullScore(150);
        sub9.setScore(135);
        Subject sub10 = mRealm.createObject(Subject.class);
        sub10.setName("数学");
        sub10.setFullScore(150);
        sub10.setScore(140);
        Subject sub11 = mRealm.createObject(Subject.class);
        sub11.setName("英语");
        sub11.setFullScore(150);
        sub11.setScore(131);
        Subject sub12 = mRealm.createObject(Subject.class);
        sub12.setName("文综");
        sub12.setFullScore(300);
        sub12.setScore(256);

        stu3.getSubjects().add(sub9);
        stu3.getSubjects().add(sub10);
        stu3.getSubjects().add(sub11);
        stu3.getSubjects().add(sub12);
        stu3.setTotalScore(getTotalScore(stu3.getSubjects()));
        stu3.setAverageScore(stu3.getTotalScore() / stu3.getSubjects().size());
        Student stu4 = mRealm.createObject(Student.class);
        stu4.setId(5);
        stu4.setAge(18);
        stu4.setGender("男");
        stu4.setName("孔明");

        Subject sub13 = mRealm.createObject(Subject.class);
        sub13.setName("语文");
        sub13.setFullScore(150);
        sub13.setScore(105.5);
        Subject sub14 = mRealm.createObject(Subject.class);
        sub14.setName("数学");
        sub14.setFullScore(150);
        sub14.setScore(148);
        Subject sub15 = mRealm.createObject(Subject.class);
        sub15.setName("英语");
        sub15.setFullScore(150);
        sub15.setScore(136);
        Subject sub16 = mRealm.createObject(Subject.class);
        sub16.setName("理综");
        sub16.setFullScore(300);
        sub16.setScore(276);

        stu4.getSubjects().add(sub13);
        stu4.getSubjects().add(sub14);
        stu4.getSubjects().add(sub15);
        stu4.getSubjects().add(sub16);
        stu4.setTotalScore(getTotalScore(stu4.getSubjects()));
        stu4.setAverageScore(stu4.getTotalScore() / stu4.getSubjects().size());
        Student stu5 = mRealm.createObject(Student.class);
        stu5.setId(4);
        stu5.setAge(18);
        stu5.setGender("男");
        stu5.setName("关羽");

        Subject sub17 = mRealm.createObject(Subject.class);
        sub17.setName("语文");
        sub17.setFullScore(150);
        sub17.setScore(100.5);
        Subject sub18 = mRealm.createObject(Subject.class);
        sub18.setName("数学");
        sub18.setFullScore(150);
        sub18.setScore(80);
        Subject sub19 = mRealm.createObject(Subject.class);
        sub19.setName("英语");
        sub19.setFullScore(150);
        sub19.setScore(111);
        Subject sub20 = mRealm.createObject(Subject.class);
        sub20.setName("理综");
        sub20.setFullScore(300);
        sub20.setScore(186);

        stu5.getSubjects().add(sub17);
        stu5.getSubjects().add(sub18);
        stu5.getSubjects().add(sub19);
        stu5.getSubjects().add(sub20);
        stu5.setTotalScore(getTotalScore(stu5.getSubjects()));
        stu5.setAverageScore(stu5.getTotalScore() / stu5.getSubjects().size());
        Student stu6 = mRealm.createObject(Student.class);
        stu6.setId(6);
        stu6.setAge(19);
        stu6.setGender("男");
        stu6.setName("张飞");

        Subject sub21 = mRealm.createObject(Subject.class);
        sub21.setName("语文");
        sub21.setFullScore(150);
        sub21.setScore(146);
        Subject sub22 = mRealm.createObject(Subject.class);
        sub22.setName("数学");
        sub22.setFullScore(150);
        sub22.setScore(134);
        Subject sub23 = mRealm.createObject(Subject.class);
        sub23.setName("英语");
        sub23.setFullScore(150);
        sub23.setScore(141);
        Subject sub24 = mRealm.createObject(Subject.class);
        sub24.setName("理综");
        sub24.setFullScore(300);
        sub24.setScore(276);

        stu6.getSubjects().add(sub21);
        stu6.getSubjects().add(sub22);
        stu6.getSubjects().add(sub23);
        stu6.getSubjects().add(sub24);
        stu6.setTotalScore(getTotalScore(stu6.getSubjects()));
        stu6.setAverageScore(stu6.getTotalScore() / stu6.getSubjects().size());
        simpleClass.getStudents().add(stu1);
        simpleClass.getStudents().add(stu2);
        simpleClass.getStudents().add(stu3);
        simpleClass.getStudents().add(stu4);
        simpleClass.getStudents().add(stu5);
        simpleClass.getStudents().add(stu6);
        setOrder(simpleClass.getStudents());
        mRealm.commitTransaction();
    }

    /**
     * 将double类型的数据转化成String，保留一位小数
     * @param num
     * @return
     */
    public static String doubleToString(double num) {
        String strNum = String.valueOf(num);
        int n = strNum.indexOf(".");
        if (n > 0) {
            String[] data = strNum.split("\\.");
            String after = data[1];
            after = after.substring(0, 1);
            return data[0] + "." + after;
        }
        else {
            return strNum + ".0";
        }
    }
}
