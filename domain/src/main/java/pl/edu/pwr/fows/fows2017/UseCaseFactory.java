package pl.edu.pwr.fows.fows2017;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Single;
import pl.edu.pwr.fows.fows2017.aux_data.FowsRxTransformerProvider;
import pl.edu.pwr.fows.fows2017.entity.FacebookPost;
import pl.edu.pwr.fows.fows2017.entity.Menu;
import pl.edu.pwr.fows.fows2017.entity.Sponsor;
import pl.edu.pwr.fows.fows2017.gateway.FacebookPostGateway;
import pl.edu.pwr.fows.fows2017.gateway.MenuGateway;
import pl.edu.pwr.fows.fows2017.gateway.SponsorGateway;
import pl.edu.pwr.fows.fows2017.usecase.FacebookPostsSharedPrefUseCase;
import pl.edu.pwr.fows.fows2017.usecase.FacebookPostsUseCase;
import pl.edu.pwr.fows.fows2017.usecase.MenuUseCase;
import pl.edu.pwr.fows.fows2017.usecase.SponsorSharedPrefUseCase;
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

    @Inject
    public UseCaseFactory(FowsRxTransformerProvider rxTransformer, MenuGateway menuGateway,
                          @Named("NetworkGateway") FacebookPostGateway facebookPostGateway,
                          @Named("LocalGateway") FacebookPostGateway facebookPostGatewaySharedPref,
                          @Named("NetworkGateway") SponsorGateway sponsorGateway,
                          @Named("LocalGateway") SponsorGateway sponsorGatewaySharedPref){
        this.menuGateway = menuGateway;
        this.rxTransformer = rxTransformer;
        this.facebookPostGateway = facebookPostGateway;
        this.facebookPostGatewaySharedPref = facebookPostGatewaySharedPref;
        this.sponsorGateway = sponsorGateway;
        this.sponsorGatewaySharedPref = sponsorGatewaySharedPref;
    }

    public UseCase<Single<List<Menu>>> getMenuUseCase(){
        return new MenuUseCase(rxTransformer, menuGateway);
    }

    public UseCase<Observable<List<FacebookPost>>> getFacebookPosts(){
        return new FacebookPostsUseCase(rxTransformer, facebookPostGateway);
    }

    public UseCase<Single<List<FacebookPost>>> getFacebookPostsSharedPref(){
        return new FacebookPostsSharedPrefUseCase(rxTransformer, facebookPostGatewaySharedPref);
    }

    public UseCase<Observable<List<List<Sponsor>>>> getSponsors(){
        return new SponsorUseCase(rxTransformer, sponsorGateway);
    }

    public UseCase<Single<List<List<Sponsor>>>> getSponsorsSharedPref(){
        return new SponsorSharedPrefUseCase(rxTransformer, sponsorGatewaySharedPref);
    }
}
