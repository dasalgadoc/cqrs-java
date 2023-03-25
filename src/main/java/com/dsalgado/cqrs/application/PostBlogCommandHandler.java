package com.dsalgado.cqrs.application;

import com.dsalgado.cqrs.domain.blog.BlogBrief;
import com.dsalgado.cqrs.domain.blog.BlogId;
import com.dsalgado.cqrs.domain.blog.BlogTitle;
import com.dsalgado.cqrs.domain.blog.BlogType;
import com.dsalgado.cqrs.domain.blog.BlogUrl;
import com.dsalgado.cqrs.domain.bus.Command;
import com.dsalgado.cqrs.domain.bus.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostBlogCommandHandler implements CommandHandler<CreateBlogCommand> {

  @Autowired private BlogCreator blogCreator;

  public PostBlogCommandHandler(BlogCreator blogCreator) {
    this.blogCreator = blogCreator;
  }

  @Override
  public <T extends Command> void invoke(T command) {
    CreateBlogCommand createBlogCommand = (CreateBlogCommand) command;

    BlogId id = new BlogId(createBlogCommand.getId());
    BlogTitle title = new BlogTitle(createBlogCommand.getTitle());
    BlogType type = new BlogType(createBlogCommand.getType());
    BlogBrief brief = new BlogBrief(createBlogCommand.getBrief());
    BlogUrl url = new BlogUrl(createBlogCommand.getUrl());

    blogCreator.create(id, title, type, brief, url);
  }
}
