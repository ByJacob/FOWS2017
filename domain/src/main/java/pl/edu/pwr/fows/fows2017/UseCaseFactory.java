package pl.edu.pwr.fows.fows2017;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Single;
import pl.edu.pwr.fows.fows2017.aux_data.FowsRxTransformerProvider;
import pl.edu.pwr.fows.fows2017.entity.ContestQuestion;
import pl.edu.pwr.fows.fows2017.entity.FacebookPost;
import pl.edu.pwr.fows.fows2017.entity.Lecture;
import pl.edu.pwr.fows.fows2017.entity.Menu;
import pl.edu.pwr.fows.fows2017.entity.OrganiserTeam;
import pl.edu.pwr.fows.fows2017.entity.Organizer;
import pl.edu.pwr.fows.fows2017.entity.PrelegentsDay;
import pl.edu.pwr.fows.fows2017.entity.Question;
import pl.edu.pwr.fows.fows2017.entity.Sponsor;
import pl.edu.pwr.fows.fows2017.entity.User;
import pl.edu.pwr.fows.fows2017.gateway.ContestQuestionGateway;
import pl.edu.pwr.fows.fows2017.gateway.FacebookPostGateway;
import pl.edu.pwr.fows.fows2017.gateway.FirebaseTokenGateway;
import pl.edu.pwr.fows.fows2017.gateway.LectureGateway;
import pl.edu.pwr.fows.fows2017.gateway.MenuGateway;
import pl.edu.pwr.fows.fows2017.gateway.OfferUrlGateway;
import pl.edu.pwr.fows.fows2017.gateway.OrganiserGateway;
import pl.edu.pwr.fows.fows2017.gateway.OrganizerGateway;
import pl.edu.pwr.fows.fows2017.gateway.QuestionGateway;
import pl.edu.pwr.fows.fows2017.gateway.QuestionnaireVersionGateway;
import pl.edu.pwr.fows.fows2017.gateway.SponsorGateway;
import pl.edu.pwr.fows.fows2017.gateway.UserGateway;
import pl.edu.pwr.fows.fows2017.usecase.AddUserAndLoginUseCase;
import pl.edu.pwr.fows.fows2017.usecase.CheckQuestionnaireVersionUseCase;
import pl.edu.pwr.fows.fows2017.usecase.FacebookPostsUseCase;
import pl.edu.pwr.fows.fows2017.usecase.GetContestUseCase;
import pl.edu.pwr.fows.fows2017.usecase.GetUserUseCase;
import pl.edu.pwr.fows.fows2017.usecase.LecturesUseCase;
import pl.edu.pwr.fows.fows2017.usecase.LoginUserExistAccountUseCase;
import pl.edu.pwr.fows.fows2017.usecase.LoginUserUseCase;
import pl.edu.pwr.fows.fows2017.usecase.LogoutUseCase;
import pl.edu.pwr.fows.fows2017.usecase.MenuUseCase;
import pl.edu.pwr.fows.fows2017.usecase.MenuWithTagUseCase;
import pl.edu.pwr.fows.fows2017.usecase.OfferUrlUseCase;
import pl.edu.pwr.fows.fows2017.usecase.OrganisersUseCase;
import pl.edu.pwr.fows.fows2017.usecase.OrganizersUseCase;
import pl.edu.pwr.fows.fows2017.usecase.QuestionnaireUseCase;
import pl.edu.pwr.fows.fows2017.usecase.SendEmailVerifyUserUseCase;
import pl.edu.pwr.fows.fows2017.usecase.SendFirebaseTokenUseCase;
import pl.edu.pwr.fows.fows2017.usecase.SendQuestionnaireUseCase;
import pl.edu.pwr.fows.fows2017.usecase.SponsorUseCase;
import pl.edu.pwr.fows.fows2017.usecase.UpdateUserEmailUseCase;
import pl.edu.pwr.fows.fows2017.usecase.UpdateUserPasswordUseCase;
import pl.edu.pwr.fows.fows2017.usecase.UpdateUserUseCase;
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
    private final LectureGateway lectureGateway;
    private final LectureGateway lectureGatewaySharedPref;
    private final OrganizerGateway organizerGateway;
    private final OrganiserGateway organiserGateway;
    private final OfferUrlGateway offerUrlGateway;
    private final QuestionGateway questionGateway;
    private final QuestionnaireVersionGateway questionnaireVersionGateway;
    private final QuestionnaireVersionGateway questionnaireVersionGatewaySharedPref;
    private final FirebaseTokenGateway firebaseTokenGateway;
    private final UserGateway userGateway;
    private ContestQuestionGateway contestQuestionGateway;

    @Inject
    public UseCaseFactory(FowsRxTransformerProvider rxTransformer, MenuGateway menuGateway,
                          @Named("NetworkGateway") FacebookPostGateway facebookPostGateway,
                          @Named("LocalGateway") FacebookPostGateway facebookPostGatewaySharedPref,
                          @Named("NetworkGateway") SponsorGateway sponsorGateway,
                          @Named("NetworkGateway") LectureGateway lectureGateway,
                          @Named("LocalGateway") LectureGateway lectureGatewaySharedPref,
                          OrganizerGateway organizerGateway,
                          OrganiserGateway organiserGateway,
                          OfferUrlGateway offerUrlGateway,
                          QuestionGateway questionGateway,
                          @Named("NetworkGateway") QuestionnaireVersionGateway questionnaireVersionGateway,
                          @Named("LocalGateway") QuestionnaireVersionGateway questionnaireVersionGatewaySharedPref,
                          FirebaseTokenGateway firebaseTokenGateway,
                          UserGateway userGateway,
                          ContestQuestionGateway contestQuestionGateway){
        this.menuGateway = menuGateway;
        this.rxTransformer = rxTransformer;
        this.facebookPostGateway = facebookPostGateway;
        this.facebookPostGatewaySharedPref = facebookPostGatewaySharedPref;
        this.sponsorGateway = sponsorGateway;
        this.lectureGateway = lectureGateway;
        this.lectureGatewaySharedPref = lectureGatewaySharedPref;
        this.organizerGateway = organizerGateway;
        this.organiserGateway = organiserGateway;
        this.offerUrlGateway = offerUrlGateway;
        this.questionGateway = questionGateway;
        this.questionnaireVersionGateway = questionnaireVersionGateway;
        this.questionnaireVersionGatewaySharedPref = questionnaireVersionGatewaySharedPref;
        this.firebaseTokenGateway = firebaseTokenGateway;
        this.userGateway = userGateway;
        this.contestQuestionGateway = contestQuestionGateway;
    }

    public UseCase<Observable<List<Menu>>> getMenuUseCase(){
        return new MenuUseCase(rxTransformer, menuGateway);
    }

    public UseCase<Observable<Menu>> isMenuWithTag(String tag){
        return new MenuWithTagUseCase(rxTransformer, menuGateway, tag);
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

    public  UseCase<Observable<List<PrelegentsDay>>> getLectures(){
        return new LecturesUseCase(rxTransformer, lectureGateway);
    }

    public UseCase<Observable<List<PrelegentsDay>>> getLecturesSharedPref(){
        return new LecturesUseCase(rxTransformer, lectureGatewaySharedPref);
    }

    public UseCase<Single<List<Organizer>>> getOrganizers(){
        return new OrganizersUseCase(rxTransformer, organizerGateway);
    }
    public UseCase<Observable<List<OrganiserTeam>>> getOrganisers(){
        return new OrganisersUseCase(rxTransformer, organiserGateway);
    }

    public UseCase<Single<String>> getOfferUrl(Locale locale){
        return new OfferUrlUseCase(rxTransformer, offerUrlGateway, locale);
    }

    public UseCase<Observable<List<Question>>> getQuestionnaire(){
        return new QuestionnaireUseCase(rxTransformer, questionGateway);
    }

    public UseCase<Observable<Boolean>> isQuestionnaireDone(){
        return new CheckQuestionnaireVersionUseCase(rxTransformer, questionnaireVersionGateway,
                questionnaireVersionGatewaySharedPref);
    }

    public UseCase<Observable<Integer>> sendQuestionnaire(List<Question> questionnaire){
        return new SendQuestionnaireUseCase(rxTransformer, questionGateway, questionnaire);
    }

    public UseCase<Observable<Boolean>> sendFirebaseToken(String token, String language){
        return new SendFirebaseTokenUseCase(rxTransformer, firebaseTokenGateway, token, language);
    }

    public UseCase<Observable<Boolean>> addUserAndLogin(String email, String password, String name,
                                                        String surname, String university,
                                                        String company){
        return new AddUserAndLoginUseCase(rxTransformer,userGateway,email, password, name, surname,
                university, company);
    }

    public UseCase<Observable<User>> getUser(){
        return new GetUserUseCase(rxTransformer, userGateway);
    }

    public UseCase<Observable<Boolean>> loginUser(String email, String password){
        return new LoginUserUseCase(rxTransformer, userGateway, email, password);
    }

    public UseCase<Observable<Boolean>> loginCurrentUser(){
        return new LoginUserExistAccountUseCase(rxTransformer, userGateway);
    }

    public UseCase<Observable<Boolean>> updateUserPassword(String password){
        return new UpdateUserPasswordUseCase(rxTransformer, userGateway, password);
    }

    public UseCase<Observable<Boolean>> updateUserEmail(String email){
        return new UpdateUserEmailUseCase(rxTransformer, userGateway, email);
    }

    public UseCase<Observable<Boolean>> updateUser(User user){
        return new UpdateUserUseCase(rxTransformer, userGateway, user);
    }

    public UseCase<Observable<Boolean>> sendEmailVerifycationUser(){
        return new SendEmailVerifyUserUseCase(rxTransformer, userGateway);
    }

    public UseCase<Observable<Boolean>> logoutCurrentUser(){
        return new LogoutUseCase(rxTransformer, userGateway);
    }

    public UseCase<Observable<List<ContestQuestion>>> getContestQuestion(){
        return new GetContestUseCase(rxTransformer, contestQuestionGateway);
    }
}
