package pl.edu.pwr.fows.fows2017.organizer;

import java.util.ArrayList;
import java.util.List;

import pl.edu.pwr.fows.fows2017.entity.Organizer;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 17.08.2017.
 */

public class OrganizerProvider {

    private final List<Organizer> organizers = new ArrayList<>();

    public OrganizerProvider() {
        organizers.add(Organizer.Builder.anOrganizer().withFirstName("Anna").withLastName("Meller")
                .withRolePL("G\u0142\u00F3wny Koordynator Projektu").withRoleEN("Main coordinator")
                .withType(Organizer.TYPE.PERSON).withEmail("anna.meller@fows.pwr.edu.pl")
                .withUrlPicture("http://fows.pwr.edu.pl/images/organizatorzy/AMeller-small.png")
                .build());
        organizers.add(Organizer.Builder.anOrganizer().withFirstName("Piotr").withLastName("Koter")
                .withRolePL("Koordynator ds. Pozyskiwania Funduszy").withRoleEN("Fundraising")
                .withType(Organizer.TYPE.PERSON).withEmail("piotr.koter@fows.pwr.edu.pl")
                .withUrlPicture("http://fows.pwr.edu.pl/images/organizatorzy/PKoter-small.png")
                .build());
        organizers.add(Organizer.Builder.anOrganizer().withFirstName("Jakub").withLastName("Rosa")
                .withRolePL("Koordynator ds. Promocji").withRoleEN("Promotion")
                .withType(Organizer.TYPE.PERSON).withEmail("jakub.rosa@fows.pwr.edu.pl")
                .withUrlPicture("http://fows.pwr.edu.pl/images/organizatorzy/JRosa-small.png")
                .build());
        organizers.add(Organizer.Builder.anOrganizer().withFirstName("Micha\u0142").withLastName("Wielgus")
                .withRolePL("Koordynator ds. Logistyki").withRoleEN("Logistics")
                .withType(Organizer.TYPE.PERSON).withEmail("michal.wielgus@fows.pwr.edu.pl")
                .withUrlPicture("http://fows.pwr.edu.pl/images/organizatorzy/MWielgus-small.png")
                .build());
        organizers.add(Organizer.Builder.anOrganizer().withFirstName("Sebastian").withLastName("Micha\u0142")
                .withRolePL("Koordynator ds. Finans\u00F3w").withRoleEN("Finances")
                .withType(Organizer.TYPE.PERSON).withEmail("sebastian.kuras@fows.pwr.edu.pl")
                .withUrlPicture("http://fows.pwr.edu.pl/images/organizatorzy/SKuras-small.png")
                .build());
        organizers.add(Organizer.Builder.anOrganizer().withFirstName("FanPage").withLastName("")
                .withRolePL("Messenger").withRoleEN("Messenger").withType(Organizer.TYPE.FACEBOOK)
                .withUrlPicture("@drawable/Messenger").withEmail("167265409968634").build());
    }

    public List<Organizer> getOrganizers() {
        return organizers;
    }
}
