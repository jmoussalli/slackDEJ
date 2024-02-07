package fr.moussalli.slackdej.repository;

import fr.moussalli.slackdej.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {}
