package poc.spring.jdbc.thyme.SpringAloneFnB;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class Troller {
    @Autowired
    Repositories repo;

    @Autowired
    UserRepos reps;
//
//    @Autowired
//    PasswordEncoder encoder;

    @GetMapping("/")
    public String base(){
        return "index";
    }

    @RequestMapping("/login")
    public String logg(){
        return "login";
    }

//    @PostMapping("/login")
//    public String logsub(@RequestParam("username") String username,@RequestParam("password") String password){
//        reps.findByUsername(username);
//    }

    @GetMapping("/signup")
    public String newOne(Model model){
        Users us=new Users();
        model.addAttribute("obj",us);
        return "signup";
    }

//    @PostMapping("/sub")
//    public String newLoad(Model model,Users users){
//        users.setPassword(encoder.encode(users.getPassword()));
//        String info=reps.newUsers(users);
//        System.out.println(info);
//        return "login";
//    }

    @GetMapping("/dele/{id}")
    public String requestDelete(Model mod, @PathVariable("id") int id){
        repo.deleteOne(id);
        return getAll(mod);
    }

    @PostMapping("/change")
    public String submitEdit(Model mod,Television tv){
        String info=repo.updateOne(tv);
        return getAll(mod);
    }

    @GetMapping("/{id}")
    public String requestEdit(Model mod, @PathVariable("id") int id){
        Television tv=repo.listOne(id).get();
        mod.addAttribute("obj",tv);
        return "Edit";
    }

    @PostMapping("/place")
    public String submitNew(Model mod,Television tv){
        String info=repo.insertOne(tv);
        if(info!=null){
            return getAll(mod);
        }
        else{
            return "Create";
        }
    }

    @GetMapping("/new")
    public String requestNew(Model model){
        Television tv=new Television();
        model.addAttribute("object",tv);
        return "Create";
    }

//    @GetMapping("/home")
//    public String home(Model mod){
//        return "Home";
//    }
    @GetMapping("/view")
    public String getAll(Model mod){
        mod.addAttribute("data",repo.listAll());
        return "Show";
    }
}
