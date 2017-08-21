package pl.edu.pwr.fows.fows2017.presenter;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.entity.Lecture;
import pl.edu.pwr.fows.fows2017.presenter.base.BasePresenter;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;
import pl.edu.pwr.fows.fows2017.view.FragmentAgendaView;
import pl.edu.pwr.fows.fows2017.view.row.FragmentAgendaRowViewHeader;
import pl.edu.pwr.fows.fows2017.view.row.FragmentAgendaRowViewItem;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 15.08.2017.
 */

public class FragmentAgendaPresenter extends BasePresenter<FragmentAgendaView> {

    private List<Lecture> lectures;

    public FragmentAgendaPresenter(UseCaseFactory factory, BaseActivityView baseActivityView) {
        super(factory, baseActivityView);
    }

    @Override
    public void onViewTaken(FragmentAgendaView view) {
        this.view = view;
        baseActivityView.enableLoadingBar();
        factory.getLectures().execute().subscribe(this::onLecturesFetchSuccess, this::onLecturesFetchFail);
    }

    private void onLecturesFetchSuccess(List<Lecture> lectures) {
        baseActivityView.disableLoadingBar();
        this.lectures = lectures;
        view.continueLoading();
    }

    private void onLecturesFetchFail(Throwable throwable) {
        if (throwable.getMessage().contains("No address")) {
            factory.getLecturesSharedPref().execute().subscribe(this::OnLecturesSharedPrefFetchSuccess);
        }
        throwable.printStackTrace();
    }

    private void OnLecturesSharedPrefFetchSuccess(List<Lecture> lectures) {
        baseActivityView.disableLoadingBar();
        this.lectures = lectures;
        if(lectures.size()<1) {
            baseActivityView.showOnError("NETWORK", true);
        } else {
            baseActivityView.showOnError("NETWORK", false);
        }
        view.continueLoading();
    }

    public int getLecturesCount() {
        return lectures.size();
    }


    public void configureRowItem(FragmentAgendaRowViewItem holder, Integer position, Locale locale) {
        if (Objects.equals(locale.getLanguage(), "pl"))
            holder.displayDescription(lectures.get(position).getDescriptionPL());
        else
            holder.displayDescription(lectures.get(position).getDescriptionEN());
        holder.displayLogo(lectures.get(position).getLogoURL());
        holder.setStatusLecture(getStatusLecture(position));
    }

    public void configureRowHeader(FragmentAgendaRowViewHeader holder, int position, Locale locale) {
        DateFormat df = new SimpleDateFormat("HH:mm", locale);
        DateFormat day = new SimpleDateFormat("dd' 'MMMM' 'yyyy", locale);
        holder.displayNameCompany(lectures.get(position).getCompany());
        holder.displaySpeaker(lectures.get(position).getSpeaker());
        holder.displaySpeakerPicture(lectures.get(position).getSpeakerPictureSmall());
        if (Objects.equals(locale.getLanguage(), "pl"))
            holder.displayTheme(lectures.get(position).getThemePL());
        else
            holder.displayTheme(lectures.get(position).getThemeEN());
        holder.displayTime(df.format(lectures.get(position).getStartTime())
                + "-" + df.format(lectures.get(position).getEndTime()));
        if (position>0){
            if (getDayInYear(position-1) < getDayInYear(position)){
                holder.displayDayHeader(day.format(lectures.get(position).getStartTime()));
            } else {
                holder.displayDayHeader("");
            }
        }
        else
            holder.displayDayHeader(day.format(lectures.get(position).getStartTime()));
        holder.setStatusLecture(getStatusLecture(position));
    }

    public void finishLoading() {
        for(int i=0; i<lectures.size(); i++){
            if (getStatusLecture(i)== FragmentAgendaRowViewHeader.STATUS.NOW){
                view.scrollToListPosition(i);
                return;
            }
        }
    }

    private Integer getDayInYear(Integer positionLectures){
        Calendar cal = Calendar.getInstance();
        cal.setTime(lectures.get(positionLectures).getStartTime());
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    private FragmentAgendaRowViewHeader.STATUS getStatusLecture(Integer position){
        Date now = new Date(System.currentTimeMillis());
        if (now.before(lectures.get(position).getStartTime()))
            return FragmentAgendaRowViewHeader.STATUS.BEFORE;
        else if (now.after(lectures.get(position).getStartTime()) && now.before(lectures.get(position).getEndTime()))
            return FragmentAgendaRowViewHeader.STATUS.NOW;
        else
            return FragmentAgendaRowViewHeader.STATUS.AFTER;
    }

}
