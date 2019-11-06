package com.custome.controller;

import com.custome.dto.User;
import com.custome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {


	@Autowired
	private UserService userService;

	@Autowired
	private ProviderSignInUtils providerSignInUtils;

	@PostMapping("/regist")
	public void regist(User user, HttpServletRequest request) {

		//不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
		String userId = user.getUsername();
		providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
	}

	@GetMapping("/{username}")
	public Object getUserByUsername(@PathVariable("username") String id) {
		return userService.getByUsername(id);
	}

	@GetMapping("/me")
	public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
		return user;
	}

	@PostMapping
	public User create(@Valid @RequestBody User user) {

		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());

		user.setId("1");
		return user;
	}

	@PutMapping("/{id:\\d+}")
	public User update(@Valid @RequestBody User user, BindingResult errors) {

		if(errors.hasErrors()){
			errors.getAllErrors().stream().forEach(error-> System.out.println(error.getDefaultMessage()));
		}

		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());

		user.setId("1");
		return user;
	}

	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable String id) {
		System.out.println(id);
	}

//	@GetMapping
//	@JsonView(User.UserSimpleView.class)
//	@ApiOperation(value = "用户查询服务")
//	public List<User> query(UserQueryCondition condition,
//			@PageableDefault(page = 2, size = 17, sort = "username,asc") Pageable pageable) {
//
//		System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
//
//		System.out.println(pageable.getPageSize());
//		System.out.println(pageable.getPageNumber());
//		System.out.println(pageable.getSort());
//
//		List<User> users = new ArrayList<>();
//		users.add(new User());
//		users.add(new User());
//		users.add(new User());
//		return users;
//	}

//	@GetMapping("/{id:\\d+}")
//	@JsonView(User.UserDetailView.class)
//	public User getInfo(@PathVariable String id) {
////		throw new RuntimeException("user not exist");
//		System.out.println("进入getInfo服务");
//		User user = new User();
//		user.setUsername("tom");
//		return user;
//	}

}