package businessPackage;

import exceptionPackage.research.ResearchDataAccessException;
import modelPackage.accountModel.IdAccount;
import modelPackage.research.MatchData;
import modelPackage.statistic.MovementData;

import java.util.ArrayList;

/**
 * Manages statistics related to chess matches for a player.
 * This class stores and computes various statistics from match data, including openings, defenses, and attacks.
 */
public class PlayerMatchStatistics {
    //possede une liste d'Opening une liste de Defense et une liste d'attaque.
    //dedans le nom, les id, String composé pour faire liste de coups

    //puis les données seront stat seront calculé en fonction de ces listes
    private ArrayList<MovementData> openingList;
    private ArrayList<MovementData> defenseList;
    private ArrayList<MovementData> attackList;
    private IdAccount accountForResearch;
    private double globalWinrate;

    /**
     * Constructor for PlayerMatchStatistics.
     * Initializes lists to store movement data for openings, defenses, and attacks.
     *
     * @param idAccount The account ID for which statistics are being compiled.
     */
    public PlayerMatchStatistics(IdAccount idAccount) {
        this.openingList = new ArrayList<>();
        this.defenseList = new ArrayList<>();
        this.attackList = new ArrayList<>();
        this.accountForResearch = idAccount;
    }

    /**
     * Overloaded constructor that accepts a primitive integer account ID.
     * @param idAccount The primitive integer ID of the account.
     */
    public PlayerMatchStatistics(int idAccount) {
        this(new IdAccount(idAccount));
    }

    /**
     * Gathers and processes match data to calculate various statistics.
     * This includes calculating win rates and adding new data to the respective lists.
     *
     * @throws ResearchDataAccessException If there is an error accessing the research data.
     */
    public void setStatistic() throws ResearchDataAccessException {
        ResearchManager researchManager = new ResearchManager();
        for (MatchData matchData : researchManager.getMatchData(accountForResearch,100)) {

            char playerColor = (matchData.getResult() == 'w' && matchData.getWinOrLose().equals("Win")) ||(matchData.getResult() == 'b' && matchData.getWinOrLose().equals("Lose"))? 'w' : 'b';

            //--------------------------------Attack------------------------------------------------
            boolean attackExist = false;
            for (MovementData attack : attackList) {
                if (attack.getName().equals(matchData.getAttack())) {
                    attackExist = true;
                    if (matchData.getWinOrLose().equals("Win")) {
                        if (playerColor == 'w') {
                            attack.addWinWith();
                        } else {
                            attack.addWinAgainst();
                        }
                    } else {
                        if (playerColor == 'w') {
                            attack.addLoseWith();
                        } else {
                            attack.addLoseAgainst();
                        }
                    }
                }
            }
            if (!attackExist) {
                MovementData newAttack = new MovementData(matchData.getAttack());
                if (matchData.getWinOrLose().equals("Win")) {
                    if (playerColor == 'w') {
                        newAttack.addWinWith();
                    } else {
                        newAttack.addWinAgainst();
                    }
                } else {
                    if (playerColor == 'w') {
                        newAttack.addLoseWith();
                    } else {
                        newAttack.addLoseAgainst();
                    }
                }
                attackList.add(newAttack);
            }
            //--------------------------------Attack end------------------------------------------------

            //--------------------------------Defense------------------------------------------------
            boolean defenseExist = false;
            for (MovementData defense : defenseList) {
                if (defense.getName().equals(matchData.getDefense())) {
                    defenseExist = true;
                    if (matchData.getWinOrLose().equals("Win")) {
                        if (playerColor == 'b') {
                            defense.addWinWith();
                        } else {
                            defense.addWinAgainst();
                        }
                    } else {
                        if (playerColor == 'b') {
                            defense.addLoseWith();
                        } else {
                            defense.addLoseAgainst();
                        }
                    }
                }
            }
            if (!defenseExist) {
                MovementData newDefense = new MovementData(matchData.getDefense());
                if (matchData.getWinOrLose().equals("Win")) {
                    if (playerColor == 'b') {
                        newDefense.addWinWith();
                    } else {
                        newDefense.addWinAgainst();
                    }
                } else {
                    if (playerColor == 'b') {
                        newDefense.addLoseWith();
                    } else {
                        newDefense.addLoseAgainst();
                    }
                }
                defenseList.add(newDefense);
            }
            //--------------------------------Defense end------------------------------------------------

            //--------------------------------Opening------------------------------------------------
            boolean openingExist = false;
            for (MovementData opening : openingList) {
                if (opening.getName().equals(matchData.getOpening())) {
                    openingExist = true;
                    if (matchData.getWinOrLose().equals("Win")) {
                        if (playerColor == 'w') {
                            opening.addWinWith();
                        } else {
                            opening.addWinAgainst();
                        }
                    } else {
                        if (playerColor == 'w') {
                            opening.addLoseWith();
                        } else {
                            opening.addLoseAgainst();
                        }
                    }
                }
            }
            if (!openingExist) {
                MovementData newOpening = new MovementData(matchData.getOpening());
                if (matchData.getWinOrLose().equals("Win")) {
                    if (playerColor == 'w') {
                        newOpening.addWinWith();
                    } else {
                        newOpening.addWinAgainst();
                    }
                } else {
                    if (playerColor == 'w') {
                        newOpening.addLoseWith();
                    } else {
                        newOpening.addLoseAgainst();
                    }
                }
                openingList.add(newOpening);
            }
            //--------------------------------Opening end------------------------------------------------
        }


        //calcul des stats globales et des stats pour chaque mouvement  (attack, defense, opening)
        int nbAttackGames = 0;
        int nbGamesWon = 0;
        for (MovementData attack : attackList) {
            nbAttackGames += attack.getWinWith() + attack.getLoseWith() + attack.getWinAgainst() + attack.getLoseAgainst();
            nbGamesWon += attack.getWinWith() + attack.getWinAgainst();
        }
        int nbDefenseGames = 0;
        for (MovementData defense : defenseList) {
            nbDefenseGames += defense.getWinWith() + defense.getLoseWith() + defense.getWinAgainst() + defense.getLoseAgainst();
            nbGamesWon += defense.getWinWith() + defense.getWinAgainst();
        }
        int nbOpeningGames = 0;
        for (MovementData opening : openingList) {
            nbOpeningGames += opening.getWinWith() + opening.getLoseWith() + opening.getWinAgainst() + opening.getLoseAgainst();
            nbGamesWon += opening.getWinWith() + opening.getWinAgainst();
        }

        globalWinrate = (double) nbGamesWon / (nbAttackGames + nbDefenseGames + nbOpeningGames) * 100;
        calculateStatistic( nbAttackGames,  nbDefenseGames,  nbOpeningGames);
    }

    private void calculateStatistic(int nbAttackGames, int nbDefenseGames, int nbOpeningGames) {
        for (MovementData attack : attackList) {
            attack.calculateStat(nbAttackGames);
        }
        for (MovementData defense : defenseList) {
            defense.calculateStat(nbDefenseGames);
        }
        for (MovementData opening : openingList) {
            opening.calculateStat(nbOpeningGames);
        }
    }


    public ArrayList<MovementData> getOpeningList() {
        return openingList;
    }

    public ArrayList<MovementData> getDefenseList() {
        return defenseList;
    }

    public ArrayList<MovementData> getAttackList() {
        return attackList;
    }

    public double getGlobalWinrate() {
        return globalWinrate;
    }

    @Override
    public String toString() {
        return "PlayerMatchStatistics{" +
                "openingList=" + openingList +
                ", defenseList=" + defenseList +
                ", attackList=" + attackList +
                '}';
    }


}
