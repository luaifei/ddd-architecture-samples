package dev.huhao.samples.ddd.blogservice.adapters.outbound.persistence.blog;

import dev.huhao.samples.ddd.blogservice.adapters.outbound.persistence.MapperIntegrationTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class BlogMapperTest extends MapperIntegrationTestBase {

    @Autowired
    private BlogMapper blogMapper;

    @Test
    void insert() {
        BlogPO newBlog = insertBlog();

        Optional<BlogPO> result = blogMapper.findById(newBlog.getId());

        assertThat(result).hasValueSatisfying(b -> assertThat(b).isEqualToComparingFieldByField(newBlog));
    }

    private BlogPO insertBlog() {
        BlogPO newBlog = new BlogPO(
                UUID.randomUUID().toString(),
                "Blog",
                "Something...",
                UUID.randomUUID().toString(),
                Instant.now(),
                Instant.now()
        );

        blogMapper.insert(newBlog);
        return newBlog;
    }
}