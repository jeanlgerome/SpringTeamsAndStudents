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

    private static final int PAGE_SIZE = 10;

    @Autowired
    private TeamService teamService;

    @GetMapping("/teamsPage/{page}")
    public String getTeams(@PathVariable int page,
                           Map<String, Object> model) {
        if (page < 1) {
            return "404";
        }
        Page<Team> teamsPage = teamService.getPageOfTeams(page, PAGE_SIZE);

        if (page > teamsPage.getTotalPages()) {
            return "404";
        }
        model.put("teams", teamsPage.getContent());
        model.put("page", page);
        model.put("previousPage", page - 1);
        model.put("nextPage", page + 1);
        model.put("lastPage", teamsPage.getTotalPages());
        return "teamsPage";
    }

    @PostMapping("/teamsPage/add")
    public String addTeam(@RequestParam String teamNumber) {
        teamService.addTeam(teamNumber);
        return "redirect:/studentsPage/" + teamNumber;
    }


}
