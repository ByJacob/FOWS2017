package pl.edu.pwr.fows.fows2017.presenter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.entity.PrelegentsDay;
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

    private List<PrelegentsDay> lectures;

    public FragmentAgendaPresenter(UseCaseFactory factory, BaseActivityView baseActivityView) {
        super(factory, baseActivityView);
    }

    @Override
    public void onViewTaken(FragmentAgendaView view) {
        this.view = view;
        baseActivityView.enableLoadingBar();
        factory.getLectures().execute().subscribe(this::onLecturesFetchSuccess, this::onLecturesFetchFail);
    }

    private void onLecturesFetchSuccess(List<PrelegentsDay> lectures) {
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

    private void OnLecturesSharedPrefFetchSuccess(List<PrelegentsDay> lectures) {
        baseActivityView.disableLoadingBar();
        this.lectures = lectures;
        if (lectures.size() < 1) {
            baseActivityView.showMessage("NETWORK", true);
        } else {
            baseActivityView.showMessage("NETWORK", false);
        }
        view.continueLoading();
    }

    public int getLecturesCount() {
        Integer sum = 0;
        for (int i = 0; i < lectures.size(); i++) {
            sum += lectures.get(i).getPrelegents().size();
        }
        return sum;
    }


    public void configureRowItem(FragmentAgendaRowViewItem holder, Integer position, Locale locale) {
        Integer dayPosition = 0;
        while (position >= lectures.get(dayPosition).getPrelegents().size()) {
            position -= lectures.get(dayPosition).getPrelegents().size();
            dayPosition++;
        }
        if (Objects.equals(locale.getLanguage(), "pl"))
            holder.displayDescription(lectures.get(dayPosition).getPrelegents().get(position).getDescriptionPL());
        else
            holder.displayDescription(lectures.get(dayPosition).getPrelegents().get(position).getDescriptionEN());
        holder.setStatusLecture(getStatusLecture(dayPosition, position));
        holder.displayLogo(lectures.get(dayPosition).getPrelegents().get(position).getLogo());
        String theme = "";
        if (Objects.equals(locale.getLanguage(), "pl"))
            theme = lectures.get(dayPosition).getPrelegents().get(position).getThemePL();
        else
            theme = lectures.get(dayPosition).getPrelegents().get(position).getThemeEN();
        holder.displayTheme(theme);
    }

    public void configureRowHeader(FragmentAgendaRowViewHeader holder, int position, Locale locale) {
        Integer dayPosition = 0;
        while (position >= lectures.get(dayPosition).getPrelegents().size()) {
            position -= lectures.get(dayPosition).getPrelegents().size();
            dayPosition++;
        }
        String dayString = lectures.get(dayPosition).getDate();
        DateFormat df = new SimpleDateFormat("HH:mm", locale);
        DateFormat day = new SimpleDateFormat("dd' 'MMMM' 'yyyy", locale);
        holder.displayNameCompany(lectures.get(dayPosition).getPrelegents().get(position).getCompany());
        holder.displaySpeaker(lectures.get(dayPosition).getPrelegents().get(position).getPrelegent());
        String theme = "";
        if (Objects.equals(locale.getLanguage(), "pl"))
            theme = lectures.get(dayPosition).getPrelegents().get(position).getThemePL();
        else
            theme = lectures.get(dayPosition).getPrelegents().get(position).getThemeEN();
        if (lectures.get(dayPosition).getPrelegents().get(position).getPrelegent().length() > 1)
            holder.displayTheme(theme);
        else {
            holder.displaySpeaker(theme);
            holder.displayTheme("");
        }
        holder.displayTime(df.format(lectures.get(dayPosition).getPrelegents().get(position).getStartTime(dayString))
                + "-" + df.format(lectures.get(dayPosition).getPrelegents().get(position).getEndTime(dayString)));

        if (position - 1 < 0) {
            holder.displayDayHeader(day.format(lectures.get(dayPosition).getPrelegents().get(position).getStartTime(dayString)));
        } else {
            holder.displayDayHeader("");
        }
        holder.setStatusLecture(getStatusLecture(dayPosition, position));
    }

    public void finishLoading() {
        for (int i = 0; i < lectures.size(); i++) {
            for (int j = 0; j < lectures.get(i).getPrelegents().size(); j++)
                if (getStatusLecture(i, j) == FragmentAgendaRowViewHeader.STATUS.NOW) {
                    view.scrollToListPosition(i);
                    return;
                }
        }
    }


    private FragmentAgendaRowViewHeader.STATUS getStatusLecture(Integer dayPosition, Integer position) {
        Date now = new Date(System.currentTimeMillis());
        String dayString = lectures.get(dayPosition).getDate();
        if (now.before(lectures.get(dayPosition).getPrelegents().get(position).getStartTime(dayString)))
            return FragmentAgendaRowViewHeader.STATUS.BEFORE;
        else if (now.after(lectures.get(dayPosition).getPrelegents().get(position).getStartTime(dayString))
                && now.before(lectures.get(dayPosition).getPrelegents().get(position).getEndTime(dayString)))
            return FragmentAgendaRowViewHeader.STATUS.NOW;
        else
            return FragmentAgendaRowViewHeader.STATUS.AFTER;
    }

    public boolean isDescription(int position) {
        Integer dayPosition = 0;
        while (position >= lectures.get(dayPosition).getPrelegents().size()) {
            position -= lectures.get(dayPosition).getPrelegents().size();
            dayPosition++;
        }
        return lectures.get(dayPosition).getPrelegents().get(position).getDescriptionPL().length() > 1;
    }
}
