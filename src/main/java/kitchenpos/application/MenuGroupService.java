package kitchenpos.application;

import java.util.List;
import java.util.stream.Collectors;
import kitchenpos.application.dto.request.menu.MenuGroupRequest;
import kitchenpos.application.dto.response.menu.MenuGroupResponse;
import kitchenpos.domain.menu.MenuGroup;
import kitchenpos.domain.repository.MenuGroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuGroupService {
    private final MenuGroupRepository menuGroupRepository;

    public MenuGroupService(MenuGroupRepository menuGroupRepository) {
        this.menuGroupRepository = menuGroupRepository;
    }

    @Transactional
    public MenuGroupResponse create(final MenuGroupRequest menuGroupRequest) {
        MenuGroup menuGroup = menuGroupRequest.toEntity();
        MenuGroup save = menuGroupRepository.save(menuGroup);

        return MenuGroupResponse.create(save);
    }

    public List<MenuGroupResponse> list() {
        List<MenuGroup> list = menuGroupRepository.findAll();

        return list.stream()
                .map(MenuGroupResponse::create)
                .collect(Collectors.toList());
    }
}
