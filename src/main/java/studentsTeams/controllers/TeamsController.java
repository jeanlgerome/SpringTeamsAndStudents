package studentsTeams.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import studentsTeams.domain.Team;
import studentsTeams.repos.TeamRepo;
import studentsTeams.service.TeamService;

import java.util.Map;

@Controller
public class TeamsController {

    @Autowired
    private TeamRepo teamRepo;
    @Autowired
    private TeamService teamService;

    @GetMapping("/teamsPage/{page}")
    public String getTeams(@PathVariable int page,
                           Map<String, Object> model) {
        if (page < 1) {
            return "404";
        }
        Pageable NPageWithTwoElements = PageRequest.of(page - 1, 10);
        Page<Team> teams = teamRepo.findAll(NPageWithTwoElements);

        if (page > teams.getTotalPages()) {
            return "404";
        }
        model.put("teams", teams.getContent());
        model.put("page", page);
        model.put("previousPage", page - 1);
        model.put("nextPage", page + 1);
        model.put("lastPage", teams.getTotalPages());
        return "teamsPage";
    }

    @PostMapping("/teamsPage/add")
    public String addTeam(@RequestParam String teamNumber) {
        teamService.addTeam(teamNumber);
        return "redirect:/studentsPage/" + teamNumber;
    }


}
