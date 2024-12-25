package jarkomdat.c03.ujian.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @GetMapping("/login")
    public String loginForm() {
        return "home_login"; // This is your login page (Thymeleaf template)
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam("email") String email) {
        if ("siswa".equalsIgnoreCase(email)) {
            return "redirect:/ujian/home";
        } else if ("perpus".equalsIgnoreCase(email)) {
            return "redirect:/ujian/perpus";
        } else {
            return "redirect:/login?error=true";
        }
    }

    @GetMapping("/ujian/home")
    public String dashboard(Model model) {
        model.addAttribute("username", "Siswa");
        model.addAttribute("exams", List.of(
                Map.of("title", "Matematika", "date", "2024-12-25", "status", "Upcoming"),
                Map.of("title", "IPA", "date", "2024-12-28", "status", "Completed"),
                Map.of("title", "IPS", "date", "2024-12-30", "status", "Upcoming")
        ));
        return "home_ujian";
    }

    @GetMapping("/perpus/home")
    public String libraryDashboard(Model model) {
        model.addAttribute("username", "Perpus User");
        model.addAttribute("books", List.of(
                Map.of("title", "Buku IPA", "dueDate", "2024-12-30", "status", "Overdue"),
                Map.of("title", "Atlas Dunia", "dueDate", "2024-12-25", "status", "Due Soon"),
                Map.of("title", "Irodori A2", "dueDate", "2024-12-28", "status", "Returned")
        ));
        return "home_perpus";
    }

    @GetMapping("/ujian")
    public String getExamPage(Model model) {
        // Example data for the exam: a list of questions and options
        List<Map<String, Object>> questions = List.of(
                Map.of(
                        "question", "Apa Ibukota Prancis?",
                        "options", List.of("Berlin", "Madrid", "Paris", "Rome"),
                        "correctAnswer", "Paris"
                ),
                Map.of(
                        "question", "Planet urutan ke-3 dari matahari?",
                        "options", List.of("Earth", "Mars", "Jupiter", "Venus"),
                        "correctAnswer", "Mars"
                ),
                Map.of(
                        "question", "Penulis Indonesia Raya'?",
                        "options", List.of("Harper Lee", "Mark Twain", "J.K. Rowling", "Ernest Hemingway"),
                        "correctAnswer", "Harper Lee"
                )
        );

        model.addAttribute("questions", questions);
        return "ujian_siswa";
    }
}
