package model;

import entity.Address;
import entity.CityInfo;
import entity.Company;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

public class DataGenerator {

    private Random ran = new Random();
    private EntityManagerFactory emf;
    private String streetAddress = "553 Hill Field Drive , Bensalem, PA 19020, 357 Foxrun Ave. , New Rochelle, NY 10801, 350 Leeton Ridge Street , Hummelstown, PA 17036, 30 Courtland Street , New City, NY 10956, 92 Hill Road , Elk River, MN 55330, 9621 Briarwood St. , Helena, MT 59601, 9284 Strawberry St. , Park Forest, IL 60466, 597 North Brook St. , Redondo Beach, CA 90278, 8088 Clark Court , Lake Jackson, TX 77566, 7906 Indian Spring St. , Hartsville, SC 29550, 30 Ketch Harbour St. , Levittown, NY 11756, 47 Bellevue Lane , Maumee, OH 43537, 968 Windfall Drive , Jupiter, FL 33458, 404 NW. Pacific Street , Akron, OH 44312, 7518 SE. Stillwater Drive , Billings, MT 59101, 9353 Overlook St. , Staten Island, NY 10301, 4 North Catherine Rd. , Brookline, MA 02446, 7507 East Roberts St. , East Hartford, CT 06118, 861 Marsh Dr. , Leland, NC 28451, 283 East Shirley St. , Jamaica Plain, MA 02130, 98 Elmwood Dr. , Linden, NJ 07036, 8470 Division St. , Harrisburg, PA 17109, 263 Golf Circle , Northville, MI 48167, 7079 Sheffield Rd. , Pickerington, OH 43147, 28 Overlook Street , La Porte, IN 46350, 42 Highland Street , Maplewood, NJ 07040, 558 Cambridge Dr. , Marlton, NJ 08053, 280 East St. , Traverse City, MI 49684, 230 George Rd. , Dacula, GA 30019, 531 Sussex Court , Mebane, NC 27302, 25 Pierce St. , Campbell, CA 95008, 70 Warren Court , Bay City, MI 48706, 2 South High St. , Reidsville, NC 27320, 8795 4th St. , Morgantown, WV 26508, 548 E. High Ridge Avenue , Muskego, WI 53150, 9 Princeton Ave. , Bountiful, UT 84010, 1 Gartner St. , Appleton, WI 54911, 35 Bay St. , Winter Haven, FL 33880, 7 N. Smith Store Street , Maryville, TN 37803, 8967B Constitution Ave. , Sioux City, IA 51106, 9248 Vermont Street , Hackensack, NJ 07601, 7137 Peg Shop St. , Westbury, NY 11590, 646 Alton Street , Howell, NJ 07731, 67 N. Williams St. , Morrisville, PA 19067, 9010 Selby Ave. , Rosedale, NY 11422, 81 South Penn Ave. , Addison, IL 60101, 677 College Rd. , Brunswick, GA 31525, 256 West Division Lane , Summerville, SC 29483, 326 Central St. , Anderson, SC 29621, 782 Pierce St. , Fort Lauderdale, FL 33308";
    private String[] streetAddresses = streetAddress.split(",");
    private String firstName = "Dann,Andy,Taunya,Pearle,Enoch,Eleonor,Vallie,Genevie,Effie,Odis,Elijah,Deloris,Mayme,Michaela,Coleman,Bethann,Maryalice,Sachiko,Mariann,Esmeralda,Eliana,Tangela,Laurette,Krista,Leena,Tamie,Stephnie,Jefferey,Halina,Mona,Tama,Kemberly,Maudie,Liliana,Kum,Georgette,Teresita,Ardath,Laurice,Clara,Saul,Collin,Bev,Annabel,Han,Werner,Vince,Marcelina,Dennis,Aliza";
    private String[] firstNames = firstName.split(",");
    private String lastName = "Petty,Davila,Stone,Choi,Atkinson,Spencer,Roberts,Pitts,Ramos,Blair,Rhodes,Garrett,Small,Adkins,Chen,Koch,Dougherty,Owens,Gordon,Hendrix,Lynch,Whitehead,Wong,Montoya,Hammond,Carroll,Hooper,Mayo,May,Villarreal,Sanford,Russo,Beck,Gilmore,Barron,Mcconnell,Nicholson,Clayton,Greene,Dillon,Fitzgerald,Palmer,Mccann,Callahan,Ross,Rosales,Chase,Mccullough,Deleon,Paul";
    private String[] lastNames = lastName.split(",");
    private String compani = "Sue Jones, Carson Dermatology Assoc, Blue Lance Software, West Boca Diagnostic Imaging, Dedicated Women's Health Spec, Society Of St Vincent DE Paul, Hillstrom Facial Plastic Surg, Gopacific.com, Spartanburg Ob-Gyn, Loraine Herbert, Natchitoches Medical Specialists LLC, Virginia Commonwealth University, Aplus Net Serve, Mountain View Assisted Living, Mid Shore Community Foundation, A Caring Place, Fairview Health Services, Mon Ami Academy Inc, Wild Kind Productions, Medical Diagnostic Assoc, Kelly Hawkins Phys Therapy, United Home Health, Sequoia In Home Care Service, Van D. Greenfield, Housing Assistance, Human Development Svc, Northern Indiana Oncology Associates Llc, Charles J Bradley, Conduct A Search, Park Place Assisted Living, St Lukes Brain & Stroke Inst, Work First Program, Capital Area Ob & Gyn Assoc, Violence Prevention Cen-SW Ill, Patton Ambulance, Complete Web World, Ricardo Moran, The Psychotherapy Center, Naturally Fresh, Orthopaedic Associates, Idaho State Veterans Home, Duddy Chiropractic, Medicus, Lifeline Mobile Medics Medivan, Keith Associates Inc, Dreamcathers Theater Inc, Crasilneck Harold B PHD, Hospital Of The University Of Pennsylvan, Children's Workshop, Talbot Hospice Foundation Inc";
    private String[] companies = compani.split(",");
    private String mails = "sekiya@gmail.com,thowell@hotmail.com,tarreau@comcast.net,lishoy@verizon.net,drezet@me.com,dvdotnet@me.com,pfitza@hotmail.com,stewwy@yahoo.ca,msusa@msn.com,rande@yahoo.com,raides@outlook.com,ivoibs@sbcglobal.net,fangorn@sbcglobal.net,keijser@outlook.com,tezbo@icloud.com,mschwartz@comcast.net,matloff@optonline.net,jshearer@sbcglobal.net,sonnen@yahoo.ca,odlyzko@yahoo.com,leakin@att.net,chaikin@hotmail.com,garyjb@att.net,burns@comcast.net,xtang@verizon.net,ahmad@hotmail.com,murty@comcast.net,oechslin@att.net,sopwith@gmail.com,ccohen@icloud.com,improv@optonline.net,cderoove@verizon.net,jemarch@icloud.com,kildjean@comcast.net,odlyzko@comcast.net,uraeus@comcast.net,citizenl@optonline.net,mcnihil@yahoo.ca,sscorpio@verizon.net,maneesh@gmail.com,marcs@hotmail.com,biglou@gmail.com,koudas@msn.com,zeller@comcast.net,mgreen@outlook.com,camenisch@aol.com,barjam@msn.com,hillct@msn.com,kohlis@sbcglobal.net,kwilliams@optonline.net,yxing@sbcglobal.net,pizza@sbcglobal.net,mirod@mac.com,wikinerd@comcast.net,gmcgath@gmail.com,boomzilla@me.com,dkeeler@mac.com,rtanter@yahoo.ca,seebs@optonline.net,gommix@live.com,gtaylor@outlook.com,hoangle@aol.com,dowdy@hotmail.com,zeller@icloud.com,chaki@outlook.com,sfoskett@live.com,scarolan@msn.com,barjam@live.com,hermes@live.com,breegster@live.com,hedwig@comcast.net,dleconte@verizon.net,ryanvm@sbcglobal.net,fluffy@outlook.com,jamuir@yahoo.com,boser@icloud.com,inico@verizon.net,ramollin@outlook.com,oechslin@live.com,garyjb@hotmail.com,knorr@optonline.net,weidai@verizon.net,gator@me.com,yruan@msn.com,lstaf@gmail.com,miami@hotmail.com,metzzo@verizon.net,yamla@comcast.net,parasite@optonline.net,portscan@me.com,kimvette@yahoo.com,dialworld@msn.com,tmccarth@aol.com,gastown@sbcglobal.net,mcnihil@sbcglobal.net,bmcmahon@outlook.com,crowemojo@sbcglobal.net,frikazoyd@aol.com,shaffei@aol.com,naoya@verizon.net";
    private String[] emails = mails.split(",");

    public DataGenerator(String database) {
        emf = Persistence.createEntityManagerFactory(database);
    }

    public static void main(String[] args) {
        new DataGenerator("PU").starter();
    }

    public void starter() {
        createHobbies();
        for (int i = 0; i < 50; i++) {
            createRandomPerson();
            createRandomCompany();
        }
    }

    public void createRandomCompany() {
        List<Phone> phones = new ArrayList();
        Company company = new Company(companies[ran.nextInt(companies.length)], "A description", 100000 + ran.nextInt(90000000), ran.nextInt(200) + 10, 2000, emails[ran.nextInt(emails.length)], phones, createRandomAddress());
        phones.add(new Phone(company, 100000 + ran.nextInt(90000000) + "", "Mobile"));
        EntityManager em = getManager();
        try {
            em.getTransaction().begin();
            em.persist(company);
            em.getTransaction().commit();
        } catch (RollbackException r) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void createRandomPerson() {
        List<Phone> phones = new ArrayList();
        Person person = new Person(firstNames[ran.nextInt(firstNames.length)], lastNames[ran.nextInt(lastNames.length)], createHobbies(), emails[ran.nextInt(emails.length)], phones, createRandomAddress());
        phones.add(new Phone(person, 100000 + ran.nextInt(90000000) + "", "Mobile"));
        EntityManager em = getManager();
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } catch (RollbackException r) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public List<Hobby> createHobbies() {
        List<Hobby> hobbies = new ArrayList();
        hobbies.add(new Hobby("football", "run around"));
        hobbies.add(new Hobby("fishing", "relax"));
        hobbies.add(new Hobby("sowing", "sow things"));
        List<Hobby> returnList = new ArrayList();
        returnList.add(hobbies.get(ran.nextInt(hobbies.size())));
        return returnList;
    }

    public Address createRandomAddress() {
        List<CityInfo> zips = getZipIds();
        int randomNumber = ran.nextInt(zips.size());
        CityInfo cityInfo = new CityInfo(zips.get(randomNumber).getZip(), zips.get(randomNumber).getCity());
        Address a = new Address(streetAddresses[ran.nextInt(streetAddresses.length)] + ran.nextInt(250), "desc", cityInfo);
        return a;
    }

    public List<CityInfo> getZipIds() {
        EntityManager em = getManager();
        List<CityInfo> zipCodes = null;
        TypedQuery<CityInfo> query = em.createQuery("SELECT c FROM CityInfo c", CityInfo.class);
        zipCodes = query.getResultList();
        return zipCodes;
    }

    private EntityManager getManager() {
        return emf.createEntityManager();
    }
}
