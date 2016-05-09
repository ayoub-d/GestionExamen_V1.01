package Controllers;

import ConnectionMongo.ConnectionEtudiant;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@SessionScoped
public class Login implements Serializable
{
    private String email;
    private String password;
    private boolean isLoggedIn = false;

    public boolean isIsLoggedIn()
    {
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean isLoggedIn)
    {
        this.isLoggedIn = isLoggedIn;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String loginEtudiant()
    {
        ConnectionEtudiant conn = new ConnectionEtudiant();
        
        if(conn.loginEtudiant(this.email, this.password))
        {
            isLoggedIn=true;
            //TODO
            //retrieve the user's cridentials :       
            
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            
            request.setAttribute("userEtudiant", conn.getListEtu());
            
            System.out.println("Login bean : logged in");
            return "Home.xhtml?faces-redirect=true";
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
            return "Login.xhtml";
        }
            
    }
    
    public String loginProfesseur()
    {
        //TODO 
        
        ConnectionEtudiant conn = new ConnectionEtudiant();
        
        if(conn.loginEtudiant(this.email, this.password))
        {
            System.out.println("metier : logged in");
            return "Home.xhtml?faces-redirect=true";
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
            return "Login.xhtml";
        }
            
    }
    
    public String logout()
    {
        isLoggedIn = false;
        return "Login.xhtml?faces-redirect=true";
    }
}