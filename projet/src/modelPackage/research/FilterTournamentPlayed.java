package modelPackage.research;

import modelPackage.accountModel.IdAccount;

import java.time.LocalDate;
import java.util.Arrays;

public class FilterTournamentPlayed {
    private IdAccount idAccountResearch;
    private String tournamentStateResearch;
    private LocalDate dateResearch;
    private final static String [] TOURNAMENT_STATES = {"final", "demi-final", "quart-final", "huitième-final", "qualifications"};

    public FilterTournamentPlayed(IdAccount idAccountResearch, String tournamentStateResearch, LocalDate dateResearch) throws IllegalArgumentException {
        setIdAccountResearch(idAccountResearch);
        setTournamentStateResearch(tournamentStateResearch);
        this.dateResearch = dateResearch;
    }

    public FilterTournamentPlayed(int idAccountResearch, String tournamentStateResearch, LocalDate dateResearch) {
        this( new IdAccount(idAccountResearch), tournamentStateResearch, dateResearch);
    }

    public void setIdAccountResearch(IdAccount idAccountResearch) {
        this.idAccountResearch = idAccountResearch;
    }

    public void setIdAccountResearch(int idAccountResearch) {
        this.idAccountResearch = new IdAccount(idAccountResearch);
    }

    public void setTournamentStateResearch(String tournamentStateResearch) {
         if (Arrays.asList(TOURNAMENT_STATES).contains(tournamentStateResearch)) {
                this.tournamentStateResearch = tournamentStateResearch;
            } else {
                throw new IllegalArgumentException("The tournament state is not valid");
            }
    }

    public Integer getIdAccountResearch() {
        return idAccountResearch.getIdAccount();
    }

    public String getTournamentStateResearch() {
        return tournamentStateResearch;
    }

    public LocalDate getDateResearch() {
        return dateResearch;
    }

    @Override
    public String toString() {
        return "FilterTournamentPlayed{" +
                "idAccountResearch=" + idAccountResearch +
                ", tournamentStateResearch='" + tournamentStateResearch + '\'' +
                ", dateResearch=" + dateResearch +
                '}';
    }
}
