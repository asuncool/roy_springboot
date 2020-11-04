package com.yihaomen.springboot.mongodb;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.yihaomen.springboot.mongodb.entity.OnOfflineMsg;
import com.yihaomen.springboot.mongodb.repository.OnOfflineRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YihaomenMongodbTests {

	@Autowired
	private OnOfflineRepository onOfflineRepository;
	
	@Test
	public void saveTest() {
		for(int i=0; i<10; i++) {
			OnOfflineMsg msg = new OnOfflineMsg(new Date(), "mac_00" + i, "routerMac", 0, 1);
			onOfflineRepository.save(msg);
		}
		
		for(int i=0; i<10; i++) {
			OnOfflineMsg msg = new OnOfflineMsg(new Date(), "mac_00" + i, "routerMac", 1, 2);
			onOfflineRepository.save(msg);
		}
	}
	
	@Test
	public void queryByUserId() {
		List<OnOfflineMsg> list = onOfflineRepository.findByUserId(1);
		list.stream().forEach(System.out::println);
	}
	
	@Test
	public void updateByMacAndUserId() {
		List<OnOfflineMsg> list = onOfflineRepository.findByUserIdAndMac(1, "mac_001");
		for(OnOfflineMsg msg : list) {
			msg.setMac(msg.getMac()+ ":modify");
		}
		onOfflineRepository.saveAll(list);
	}
	
	@Test
	public void deleteByUserId() {
		List<OnOfflineMsg> list = onOfflineRepository.findByUserId(2);
		onOfflineRepository.deleteAll(list);
	}
	
	@Test
	public void findByExample() {		
		OnOfflineMsg msg = new OnOfflineMsg();
		msg.setRouterMac("routerMac");
		msg.setUserId(1);
		Example<OnOfflineMsg> example = Example.of(msg);
		List<OnOfflineMsg> list = onOfflineRepository.findAll(example, Sort.by(Direction.ASC, "mac"));
		list.stream().forEach(System.out::println);
	}
	
	@Test
	public void findByExamplePagination() {
		OnOfflineMsg msg = new OnOfflineMsg();
		msg.setRouterMac("routerMac");
		msg.setUserId(1);
		Example<OnOfflineMsg> example = Example.of(msg);
		
		PageRequest page = PageRequest.of(0, 2, Sort.by(Direction.ASC, "mac"));
		Page<OnOfflineMsg> pageList = onOfflineRepository.findAll(example, page);
		List<OnOfflineMsg> list = pageList.getContent();
		System.out.println("====================");
		list.stream().forEach(System.out::println);
	}

}
