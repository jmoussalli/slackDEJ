package fr.moussalli.slackdej.repository;

import fr.moussalli.slackdej.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

    // UTiliser des ChannelDTOs
//    public List<Channel> findAllBy();
//
//    public Optional<Channel> findOneById(Long id);
//
//    public Optional <Channel> findOneByDeletable(Boolean deletable);
}
