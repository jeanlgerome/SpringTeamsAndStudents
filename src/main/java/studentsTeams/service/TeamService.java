package studentsTeams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import studentsTeams.domain.Team;
import studentsTeams.repos.TeamRepo;

@Component
public class TeamService {

    @Autowired
    private TeamRepo teamRepo;

    public Page<Team> getPageOfTeams(int page, int size){
        Pageable NPageWithTwoElements = PageRequest.of(page - 1, size);
        Page<Team> teams = teamRepo.findAll(NPageWithTwoElements);
        return teams;
    }
    public void addTeam(String teamNumber) {
        Team oldTeam = teamRepo.findFirstByTeamNumber(teamNumber);

        if (oldTeam == null) {
            Team newTeam = new Team(teamNumber);
            teamRepo.save(newTeam);
        }
    }

    public boolean teamIsExisting(String teamNumber) {
        Team team = teamRepo.findFirstByTeamNumber(teamNumber);

        if (team != null) {
            return true;
        }
        return false;
    }
}
