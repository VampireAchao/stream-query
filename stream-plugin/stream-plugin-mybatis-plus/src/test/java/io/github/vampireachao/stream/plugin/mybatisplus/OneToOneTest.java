package io.github.vampireachao.stream.plugin.mybatisplus;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import io.github.vampireachao.stream.plugin.mybatisplus.pojo.po.UserInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 一对一测试
 *
 * @author VampireAchao &lt; achao1441470436@gmail.com &gt;
 */
@MybatisPlusTest
class OneToOneTest {

    @Test
    void testQuery() {
        List<Long> userIds = Arrays.asList(1L, 2L, 3L, 4L, 5L);
        Map<Long, UserInfo> idUserMap = OneToOne.query(userIds, UserInfo::getId);
        Assertions.assertEquals(5, idUserMap.size());

        Map<Long, String> userIdNameMap = OneToOne.query(userIds, UserInfo::getId, UserInfo::getName);
        Assertions.assertEquals(5, userIdNameMap.size());

        userIdNameMap = OneToOne.query(w -> w.le(UserInfo::getAge, 22), userIds, UserInfo::getId, UserInfo::getName);
        Assertions.assertEquals(3, userIdNameMap.size());


        idUserMap = OneToOne.query(1L, UserInfo::getId);
        Assertions.assertEquals(1, idUserMap.size());

        userIdNameMap = OneToOne.query(1L, UserInfo::getId, UserInfo::getName);
        Assertions.assertEquals(1, userIdNameMap.size());

        userIdNameMap = OneToOne.query(w -> w.le(UserInfo::getAge, 22), 1L, UserInfo::getId, UserInfo::getName);
        Assertions.assertEquals(1, userIdNameMap.size());

        Map<Long, Boolean> query = OneToOne.query(w -> w.select(UserInfo::getId, UserInfo::getName), userIds, UserInfo::getId, userInfo -> userInfo.getName() != null && userInfo.getName().contains("a"));
        Assertions.assertEquals(2, query.values().stream().filter(Boolean::booleanValue).count());
    }

}
