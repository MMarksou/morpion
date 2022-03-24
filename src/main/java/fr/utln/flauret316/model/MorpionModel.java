package fr.utln.flauret316.model;

import java.util.Arrays;

/**
 * Ceci est notre classe model du jeu du morpion. Vu que nous avons un petit projet,
 * notre model joue aussi le rôle de controller.
 *
 * Cette classe contient le nom de chaque joueur ainsi que la grille de jeu.
 */

public class MorpionModel {
    private final String joueur1;
    private final String joueur2;
    private int joueurActuel;
    private int cpt;
    private Integer[][] grille;

    public MorpionModel(String name1, String name2, Integer taille) {
        if(name1.equals(name2)) throw new IllegalArgumentException("Veuillez choisir un nom différent de joueur");
        this.joueur1 = name1;
        this.joueur2 = name2;
        this.grille = new Integer[taille][taille];
        this.joueurActuel = 1;
        this.cpt = 0;

        for (Integer[] integers : grille) {
            Arrays.fill(integers, 0);
        }
    }

    public int getCpt() {
        return cpt;
    }

    public void setCpt(int cpt) {
        this.cpt = cpt;
    }

    public Integer[][] getGrille() {
        return grille;
    }

    public void setGrille(Integer[][] grille) {
        this.grille = grille;
    }


    public String getJoueur1() {
        return joueur1;
    }

    public String getJoueur2() {
        return joueur2;
    }

    public int getJoueurActuel() {
        return joueurActuel;
    }

    public void setJoueurActuel(int joueurActuel) {
        this.joueurActuel = joueurActuel;
    }

    /**
     * Vérifie si le coup est légitime
     * @param position position de son coup
     * @return true si c'est correct, false dans le cas contraire
     */
    public Boolean place (Integer[] position) {

        Integer[][] grilletmp = getGrille();

        if (grilletmp[position[0]][position[1]] == 0) {

                grilletmp[position[0]][position[1]] = getJoueurActuel();
                setGrille(grilletmp);

            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Permet de généraliser toutes les vérifications lors d'un coup joué
     * @param joueur le joueur jouant le coup
     * @param x position x de son coup dans le tableau
     * @param y position y de son coup dans le tableau
     * @return true si gagnant sinon false
     */
    public Boolean verification (int joueur, int x, int y) {

        Integer[][] tmp = getGrille();

        if (Boolean.TRUE.equals(diagonale1(tmp, 0, 0, joueur))){
            return true;
        }

        if (Boolean.TRUE.equals(diagonale2(tmp, 0, tmp.length-1, joueur))){
            return true;
        }

        if (Boolean.TRUE.equals(colonne(tmp, 0, y, joueur))){
            return true;
        }

        return Boolean.TRUE.equals(ligne(tmp, x, 0, joueur));
    }

    /**
     * Vérifie s'il n'y a pas un coup gagnant sur la diagonale partant d'en haut à gauche et allant en bas à droite
     * @param tmp la grille
     * @param x position x dans le tableau
     * @param y position y dans le tableau
     * @param nom le joueur jouant le coup
     * @return true si coup gagnant, false dans le cas contraire
     */
    public Boolean diagonale1(Integer[][] tmp, int x, int y, int nom){

        if (tmp[x][y] != nom && x == tmp.length-1 && y == tmp.length-1) {
            return false;
        }

        if (tmp[x][y] == nom && x == tmp.length-1 && y == tmp.length-1) {
            return true;
        }

        if (tmp[x][y] != nom) {
            return false;
        }

        return Boolean.TRUE.equals(diagonale1(tmp, x + 1, y + 1, nom));
    }

    /**
     * La même que diagonale1 mais s'occupe de la diagonale partant d'en haut à droite jusqu'en bas à gauche
     * @param tmp la grille
     * @param x position x dans le tableau
     * @param y position y dans le tableau
     * @param nom le joueur jouant le coup
     * @return true si coup gagnant, false si cas contraire
     */
    public Boolean diagonale2(Integer[][] tmp, int x, int y, int nom){

        if (tmp[x][y] != nom && x == tmp.length-1 && y == 0) {
            return false;
        }

        if (tmp[x][y] == nom && x == tmp.length-1 && y == 0) {
            return true;
        }

        if (tmp[x][y] != nom) {
            return false;
        }

        return Boolean.TRUE.equals(diagonale2(tmp, x + 1, y - 1, nom));
    }

    /**
     * Vérifie s'il n'y a pas une colonne gagnante
     * @param tmp la grille
     * @param x la position x de la colonne dans le tableau
     * @param y la position y de la colonne dans le tableau
     * @param nom le joueur jouant le coup
     * @return retourne true si coup gagnant, false dans le cas contraire
     */
    public Boolean colonne(Integer[][] tmp, int x, int y, int nom) {
        if (tmp[x][y] != nom && x == tmp.length-1) {
            return false;
        }
        if (tmp[x][y] == nom && x == tmp.length-1) {
            return true;
        }
        if (tmp[x][y] != nom) {
            return false;
        }

        return Boolean.TRUE.equals(colonne(tmp, x + 1, y, nom));
    }

    /**
     * Vérifie s'il n'y a pas une ligne gagnante
     * @param tmp la grille
     * @param x la position x de la ligne dans le tableau
     * @param y la position y de la ligne dans le tableau
     * @param nom le joueur jouant le coup
     * @return retourne true si coup gagnant, false dans le cas contraire
     */
    public Boolean ligne(Integer[][] tmp, int x, int y, int nom) {
        if (tmp[x][y] != nom && y == tmp.length-1) {
            return false;
        }

        if (tmp[x][y] == nom && y == tmp.length-1) {
            return true;
        }

        if (tmp[x][y] != nom) {
            return false;
        }

        return Boolean.TRUE.equals(ligne(tmp, x,y + 1, nom));
    }

    /**
     * Permet de gérer le coup, de récupérer le booléen de retour pour la vérification et d'en avertir la vue
     * @param x correspond à la ligne du tableau
     * @param y correspond à la colonne du table
     * @return 1 ou 2 selon le joueur qui a gagné, 3 si match nul, 0 si coup nul, -1 dans les autres cas
     */
    public Integer jouer(int x, int y) {
        Integer[] pos = {x, y};
        if (place(pos)) {
            if (Boolean.TRUE.equals(verification(getJoueurActuel(), x, y))) {
                return getJoueurActuel();
            }
            if(getCpt() == getGrille().length*getGrille().length-1){
                return 3;
            }
            else {
                setCpt(getCpt()+1);
                setJoueurActuel(getJoueurActuel() % 2 + 1);
                return 0;
            }
        }
        else {
            return -1;
        }
    }
}