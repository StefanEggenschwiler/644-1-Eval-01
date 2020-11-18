package ch.hesso.remembeer.database;

import android.os.AsyncTask;
import android.util.Log;

import ch.hesso.remembeer.database.entity.BeerEntity;
import ch.hesso.remembeer.database.entity.BreweryEntity;
/**
 * Classe permmettant d'initialiser la base de donnee au niveau des donnees uniquement
 */
public class DbInitializer {
    public static void initDB(final ApplicationDatabase db) {
        Log.i("DbInitializer", "Inserting demo data.");
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    /**
     * Population avec des donnees test
     */

    private static void populateWithTestData(ApplicationDatabase db) {

        db.beerDao().deleteAll();
        db.breweryDao().deleteAll();

        // ajout des differentes bieres
        addBeer(db,"Blanche", "La VALAISANNE Blanche s’habille avec une couleur jaune clair et une magnifique turbidité naturelle de la levure.\n" +
                        "\n" +
                        "Grâce à l'ajout de l'arôme noble du houblon Amarillo à la jeune bière, les notes douces de clou de girofle et de levure de la bière blanche " +
                        "se mêlent aux notes fruitées d'abricot et d'agrumes du houblon. En finale, notre Blanche, tendre et corsée, convainc par sa finesse et sa légère amertume.",
                "Brasserie Valaisanne", "", 0.33, 5.5, 12);

        addBeer(db,"De Cave", "La VALAISANNE Bière de Cave est une spécialité non filtrée. Sa mousse blanche va parfaitement avec  sa couleur jaune doré. La Bière de Cave est " +
                        "agréable à boire. Elle rafraîchit et tonifie le palais avec un léger malt et une note de levure fraîche et fruitée. L'amertume est puissante," +
                        " mais fine, et complète l'expérience gustative de manière décisive.",
                "Brasserie Valaisanne", "", 0.33, 5.4, 12);

        addBeer(db,"Pale ALe", "La VALAISANNE Pale Ale est une Single Hop Pale Ale aromatique fruitée, grâce au houblon aromatique de la variété Bravo. " +
                        "La couleur brun renard de la bière, offre des reflets légèrement cuivrés et une mousse caramélisée, pour une harmonie parfaite. Les notes aromatiques de malt " +
                        "torréfié sont accompagnées de notes discrètes de fruits secs et de mandarines fruitées. " +
                        "En bouche, crémeuse et relaxante, la Pale Ale a une finale heureuse, qui mène à la prochaine gorgée",
                "Brasserie Valaisanne", "", 0.33, 5.2, 12);

        addBeer(db,"La Djeronimo", "White IPA avec houblons Crystal et Citra à foison ! Disponible en 33cl, pression, fût de 20 litres","BFM", "", 0.3,5.3, 10);
        addBeer(db,"La Brouette", "Ambrée aux tendances Feng Shui, La Brouette, une ambrée, maltée avec une pointe de thé vert japonais ! Disponible en 33cl, pression, fût de 20 litress","BFM", "", 0.3,5, 12);
        addBeer(db,"La Salamandre", "Blanche ! La Salamandre, la BFM originale, bière de blé, fraîche et épicée. Disponible en 33cl, pression, fût de 20 litres","BFM", "", 0.3,5.5, 11);



        // ajout des differentes brasseries
        addBrewery(db, "Valaisanne", "Route du Rawyl 30", "Sion", "brasserie_valaisanne", "Nos brasseurs très motivés et engagés produisent des bières artisanales en Valais avec passion et sans compromis. Notre philosophie est façonnée par le côté rude de la vie dans les Alpes. Les caractéristiques p" +
                "ortes de la population VALAISANNE se reflètent également dans nos produits. Pas de pente trop raide, pas de pente trop forte, vous tombez, " +
                "vous vous relevez et continuez. C'est avec cette obsession, presque obstination, que nous recherchons en permanence les meilleurs ingrédients et que nous optimisons les procédés pour donner à nos bières leur caractère fort. \n" +
                "\n" + "L'eau de brassage utilisée provient de la source La Fille. Elle s'élève à une altitude de 1800 m au-dessus du niveau de la mer, près d'Arbaz, dans la vallée de la Sionne. ", "www.valaisanne.ch");

        addBrewery(db, "Docteur Gab's", "ZI du Verney 1", "Puidoux", "docteur_gabs", "\n" +
                "\n" +
                "Fondée grâce à un kit de brassage en 2001 par trois potes d’enfance qui sont toujours aux manettes, développée avec passion durant des années, la Brasserie Dr. Gab’s s’est imposée comme pionnière de la scène Craft en Suisse.\n" +
                "\n" +
                "Une fine équipe s’active (presque) jour et nuit, animée par une même passion et une même ambition : ne pas accepter le constat qu’une bière est forcément blonde, légère et insipide, et tirer vers le haut  la qualité et la diversité de la bière dans nos contrées.\n" +
                "\n" +
                "Fidèle à son esprit d’origine, Dr. Gab’s souhaite simplement porter sa passion de la bière au grand public, créer, s’amuser, créer à nouveau, partager, faire découvrir, et " +
                "offrir des bons moments à celles et ceux qui l’accompagnent dans ses aventures.\n", "docteurgabs.ch");

        addBrewery(db, "BFM", "Chemin des Buissons 8", "Saignelégier", "bfm", "Jérôme Rebetez s’attelle à produire le meilleur : les matières premières sont choisies avec soin et il privilégie la qualité des ingrédients, toujours originaux et difficiles à intégrer, comme les poivres de Sarawak, les sauges et autres épices. " +
                "Le savoir-faire du jeune brasseur le positionne alors rapidement comme l’artisan des bières riches aux bouquets complexes, jouissant d’un corps et d’une longueur en bouche remarquables. Sa quête permanente de la subtilité des arômes et ses expériences en oenologie poussent Jérôme Rebetez à concocter des produits comparables aux meilleurs vins.\n" +
                "\n" +
                "Le brasseur jurassien génère des cuvées singulièrement originales, toujours en quantités limitées, du cousu main, comme il l’énonce. L’Abbaye de Saint Bon-Chien, une bière BFM élevée 12 mois en fûts de chêne vient d'être reconnue par le " +
                "prestigieux New York Times comme l'une des meilleures bières du monde.\"", "www.brasseriebfm.ch");
        addBrewery(db, "La Sierrvoise", "Z.I. Ile Falcon,", "Sierre", "sierrvoise", "Depuis 1997, la brasserie artisanale « La Sierrvoise » a su développer une bière de caractère, à l’image du Valais.\n" +
                "\n" +
                "Déclinée de différentes manières, la bière artisanale « La Sierrvoise » se déguste selon vos goûts et vos affinités : claire, noire, blanche ou rousse. Toutes ces bières artisanales développent des arômes particuliers, à savourer et à découvrir absolument.", "www.sierrvoise.ch");
        addBrewery(db, "Hoppy People", "Rue des Sablons 11", "Sierre","hoppy_people", "Hoppy people a été créé en 2016 par deux amoureux du houblon, Olivier Brighenti et David Bonjour. Hoppy People suit les traces des révolutionnaires de la bière artisanale du début des années 80, venus des États-Unis et de Scandinavie.\n" +
                "Après 5 ans de homebrewing, nous avons fait le grand saut et acheté une ligne de brassage 10HL et 6 fermenteurs de 24 hectolitres chacun. La brasserie est située à Sierre (Valais) dans la zone industrielle, dans un entrepôt couvert de panneaux solaires ; ce qui permet d'alimenter tout le processus de production avec une énergie propre. " +
                "Une caractéristique intéressante puisque Sierre est la ville la plus ensoleillée de Suisse. 5 fermenteurs supplémentaires de 45HL chacun ont été ajoutés en 2018.\n", "www.hoppypeople.com" );

    }

    private static void addBeer(ApplicationDatabase db, String name, String description, String from, String pathImage, double capacity, double alcool, double acidity ) {
        BeerEntity b = new BeerEntity();
        b.setName(name);
        b.setDescription(description);
        b.setFrom(from);
        b.setImage(pathImage);
        b.setCapacity(capacity);
        b.setAlcool(alcool);
        b.setAcidity(acidity);
        db.beerDao().insert(b);
    }


    private static void addBrewery(ApplicationDatabase db, String name, String address, String city, String pathImage, String description, String web ){
        BreweryEntity b = new BreweryEntity();
        b.setName(name);
        b.setAddress(address);
        b.setCity(city);
        b.setImage(pathImage);
        b.setDescription(description);
        b.setWeb(web);
        db.breweryDao().insert(b);

    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ApplicationDatabase database;

        PopulateDbAsync(ApplicationDatabase db) {
            database = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(database);
            return null;
        }

    }
}
