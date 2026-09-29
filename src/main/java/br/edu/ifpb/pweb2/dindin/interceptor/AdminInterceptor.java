package br.edu.ifpb.pweb2.dindin.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import br.edu.ifpb.pweb2.dindin.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component 
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        if (session != null) {
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

            if (usuario != null && usuario.isAdmin()) {
                return true; // Possui permissão de admin
            }
        }

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return false;
    }
}
