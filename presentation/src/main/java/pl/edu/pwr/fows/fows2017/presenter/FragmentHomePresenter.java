package pl.edu.pwr.fows.fows2017.presenter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.entity.Lecture;
import pl.edu.pwr.fows.fows2017.entity.Sponsor;
import pl.edu.pwr.fows.fows2017.presenter.base.BasePresenter;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;
import pl.edu.pwr.fows.fows2017.view.FragmentHomeView;
import pl.edu.pwr.fows.fows2017.view.row.FragmentAgendaRowViewHeader;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 03.08.2017.
 */

public class FragmentHomePresenter extends BasePresenter<FragmentHomeView>{

    private List<Lecture> lectures = new ArrayList<>();
    private List<List<Sponsor>> sponsors = new ArrayList<>();
    private Locale locale;
    private static Integer lastPos1=-1;

    public FragmentHomePresenter(UseCaseFactory factory, BaseActivityView baseActivityView) {
        super(factory, baseActivityView);
    }

    public void clickItem(String tag) {
        baseActivityView.changeMainFragment(tag);
    }

    @Override
    public void onViewTaken(FragmentHomeView view) {
        this.view = view;
        baseActivityView.disableLoadingBar();
        factory.getLectures().execute().subscribe(this::onLecturesFetchSuccess, this::onLecturesFetchFail);
        factory.getSponsors().execute().subscribe(this::onSponsorsFetchSuccess);
    }

    private void onSponsorsFetchSuccess(List<List<Sponsor>> lists) {
        this.sponsors = lists;
        displaySponsor();
    }

    public void setLocale(Locale locale){
        this.locale = locale;
    }

    private void onLecturesFetchSuccess(List<Lecture> lectures) {
        this.lectures = lectures;
        displayLecture(false);
    }

    private void onLecturesFetchFail(Throwable throwable) {
        if (throwable.getMessage().contains("No address"))
            factory.getLecturesSharedPref().execute().subscribe(this::onLecturesSharedPrefFetchSuccess);
        throwable.printStackTrace();
    }

    private void onLecturesSharedPrefFetchSuccess(List<Lecture> lectures) {
        this.lectures = lectures;
        displayLecture(false);
    }

    public void displayLecture(Boolean isNext){
        DateFormat df = new SimpleDateFormat("dd MMMM HH:mm", locale);
        Date now = new Date(System.currentTimeMillis());
        for(int i=0; i<lectures.size() && !isNext; i++){
            if (now.after(lectures.get(i).getStartTime()) && now.before(lectures.get(i).getEndTime())) {
                String theme;
                if (Objects.equals(locale.getLanguage(), "pl"))
                    theme = lectures.get(i).getThemePL();
                else
                    theme = lectures.get(i).getThemeEN();
                view.displayLecture(df.format(lectures.get(i).getStartTime()), theme, false);
                return;
            }
            if (i==lectures.size()-1)
                isNext = !isNext;
        }
        for(int i=0; i<lectures.size() && isNext; i++){
            if (now.before(lectures.get(i).getStartTime())){
                String theme;
                if (Objects.equals(locale.getLanguage(), "pl"))
                    theme = lectures.get(i).getThemePL();
                else
                    theme = lectures.get(i).getThemeEN();
                view.displayLecture(df.format(lectures.get(i).getStartTime()), theme, true);
                return;
            }
        }
        view.displayLecture("","",null);

    }

    public void displaySponsor(){
        Random random = new Random();
        Integer pos1 = random.nextInt(sponsors.size());
        while(Objects.equals(pos1, lastPos1))
        {
            pos1 = random.nextInt(sponsors.size());
        }
        lastPos1 = pos1;
        Integer pos2 = random.nextInt(sponsors.get(pos1).size());
        while(pos2<0){
            pos2 = random.nextInt(sponsors.get(pos1).size());
        }
        view.displayLogo(sponsors.get(pos1).get(pos2).getUrl());
    }
}
