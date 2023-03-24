package com.dsalgado.cqrs.infrastructure.entrypoint;

import com.dsalgado.cqrs.domain.blog.CreateBlogCommand;
import com.dsalgado.cqrs.domain.bus.CommandBus;
import com.dsalgado.cqrs.domain.bus.CommandBusFactory;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostBlogController {

  @Resource private CommandBusFactory commandBusFactory;
  private CommandBus commandBus;

  @PostConstruct
  public void initializer() {
    commandBus = commandBusFactory.getCommandBus();
  }

  @PostMapping("/blog")
  public HttpStatus createBlog(@RequestBody CreateBlogCommand request) {
    if (request.isValid()) {
      return HttpStatus.BAD_REQUEST;
    }

    this.dispatch(request);
    return HttpStatus.OK;
  }

  private void dispatch(CreateBlogCommand request) {
    commandBus.dispatch(request);
  }
}
