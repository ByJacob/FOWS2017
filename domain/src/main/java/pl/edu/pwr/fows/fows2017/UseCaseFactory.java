package pl.edu.pwr.fows.fows2017;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Single;
import pl.edu.pwr.fows.fows2017.aux_data.FowsRxTransformerProvider;
import pl.edu.pwr.fows.fows2017.entity.FacebookPost;
import pl.edu.pwr.fows.fows2017.entity.Lecture;
import pl.edu.pwr.fows.fows2017.entity.Menu;
import pl.edu.pwr.fows.fows2017.entity.Organizer;
import pl.edu.pwr.fows.fows2017.entity.Sponsor;
import pl.edu.pwr.fows.fows2017.gateway.FacebookPostGateway;
import pl.edu.pwr.fows.fows2017.gateway.LectureGateway;
import pl.edu.pwr.fows.fows2017.gateway.MenuGateway;
import pl.edu.pwr.fows.fows2017.gateway.OfferUrlGateway;
import pl.edu.pwr.fows.fows2017.gateway.OrganizerGateway;
import pl.edu.pwr.fows.fows2017.gateway.SponsorGateway;
import pl.edu.pwr.fows.fows2017.usecase.FacebookPostsUseCase;
import pl.edu.pwr.fows.fows2017.usecase.LecturesUseCase;
import pl.edu.pwr.fows.fows2017.usecase.MenuUseCase;
import pl.edu.pwr.fows.fows2017.usecase.OfferUrlUseCase;
import pl.edu.pwr.fows.fows2017.usecase.OrganizersUseCase;
import pl.edu.pwr.fows.fows2017.usecase.SponsorUseCase;
import pl.edu.pwr.fows.fows2017.usecase.base.UseCase;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.07.2017.
 */

public class UseCaseFactory {

    private final MenuGateway menuGateway;
    private final FowsRxTransformerProvider rxTransformer;
    private final FacebookPostGateway facebookPostGateway;
    private final FacebookPostGateway facebookPostGatewaySharedPref;
    private final SponsorGateway sponsorGateway;
    private final SponsorGateway sponsorGatewaySharedPref;
    private final LectureGateway lectureGateway;
    private final LectureGateway lectureGatewaySharedPref;
    private final OrganizerGateway organizerGateway;
    private final OfferUrlGateway offerUrlGateway;

    @Inject
    public UseCaseFactory(FowsRxTransformerProvider rxTransformer, MenuGateway menuGateway,
                          @Named("NetworkGateway") FacebookPostGateway facebookPostGateway,
                          @Named("LocalGateway") FacebookPostGateway facebookPostGatewaySharedPref,
                          @Named("NetworkGateway") SponsorGateway sponsorGateway,
                          @Named("LocalGateway") SponsorGateway sponsorGatewaySharedPref,
                          @Named("NetworkGateway") LectureGateway lectureGateway,
                          @Named("LocalGateway") LectureGateway lectureGatewaySharedPref,
                          OrganizerGateway organizerGateway,
                          OfferUrlGateway offerUrlGateway){
        this.menuGateway = menuGateway;
        this.rxTransformer = rxTransformer;
        this.facebookPostGateway = facebookPostGateway;
        this.facebookPostGatewaySharedPref = facebookPostGatewaySharedPref;
        this.sponsorGateway = sponsorGateway;
        this.sponsorGatewaySharedPref = sponsorGatewaySharedPref;
        this.lectureGateway = lectureGateway;
        this.lectureGatewaySharedPref = lectureGatewaySharedPref;
        this.organizerGateway = organizerGateway;
        this.offerUrlGateway = offerUrlGateway;
    }

    public UseCase<Observable<List<Menu>>> getMenuUseCase(){
        return new MenuUseCase(rxTransformer, menuGateway);
    }

    public UseCase<Observable<List<FacebookPost>>> getFacebookPosts(){
        return new FacebookPostsUseCase(rxTransformer, facebookPostGateway);
    }

    public UseCase<Observable<List<FacebookPost>>> getFacebookPostsSharedPref(){
        return new FacebookPostsUseCase(rxTransformer, facebookPostGatewaySharedPref);
    }

    public UseCase<Observable<List<List<Sponsor>>>> getSponsors(){
        return new SponsorUseCase(rxTransformer, sponsorGateway);
    }

    public UseCase<Observable<List<List<Sponsor>>>> getSponsorsSharedPref(){
        return new SponsorUseCase(rxTransformer, sponsorGatewaySharedPref);
    }

    public  UseCase<Observable<List<Lecture>>> getLectures(){
        return new LecturesUseCase(rxTransformer, lectureGateway);
    }

    public UseCase<Observable<List<Lecture>>> getLecturesSharedPref(){
        return new LecturesUseCase(rxTransformer, lectureGatewaySharedPref);
    }

    public UseCase<Single<List<Organizer>>> getOrganizers(){
        return new OrganizersUseCase(rxTransformer, organizerGateway);
    }

    public UseCase<Single<String>> getOfferUrl(Locale locale){
        return new OfferUrlUseCase(rxTransformer, offerUrlGateway, locale);
    }
}
