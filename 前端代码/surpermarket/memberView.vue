<template>
  <div>
    <el-container style="height: 700px; border: 1px solid #eee">
      <el-header style="font-size: 40px; background-color: #409Ea8">超市收银系统</el-header>
      <el-container>
        <el-aside width="200px" style="background-color: #dfffff">
          <el-menu :default-openeds="['1', '6']" style="background-color: #408Ea0">
            <el-submenu index="1">
              <template slot="title"><i class="el-icon-menu"></i>导航</template>
              <el-menu-item index="1-1">
                  <router-link to="/member">会员管理</router-link>
              </el-menu-item>
              <el-menu-item index="1-2">
                  <router-link to="/product">商品管理</router-link>
              </el-menu-item>
              <el-menu-item index="1-3">
                  <router-link to="/shopping">购物处理</router-link>
              </el-menu-item>
              <el-menu-item index="1-4">
                  <router-link to="/order">订单管理</router-link>
              </el-menu-item>
              <el-menu-item index="1-5">
                  <router-link to="/salesSummary">销售情况汇总</router-link>
              </el-menu-item>
              
            </el-submenu>
          </el-menu>
        </el-aside>

        <el-main>
          <!-- 会员查询功能 -->
           
          <!-- 显示会员信息 -->
          <el-table :data="members" style="width: 100%; margin-top: 20px;">
            <el-table-column prop="memberID" label="会员ID"></el-table-column>
            <el-table-column prop="memberName" label="姓名"></el-table-column>
            <el-table-column prop="telephone" label="电话号码"></el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button size="mini" @click="showEditMemberDialog(scope.row)">编辑</el-button>
                <el-button size="mini" type="danger" @click="deleteMember(scope.row.memberID)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-dialog title="编辑会员" :visible.sync="editMemberDialogVisible">
            <el-form :model="memberForm">
              <el-form-item label="姓名">
                <el-input v-model="memberForm.memberName"></el-input>
              </el-form-item>
              <el-form-item label="电话号码">
                <el-input v-model="memberForm.telephone"></el-input>
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
              <el-button @click="editMemberDialogVisible = false">取消</el-button>
              <el-button type="primary" @click="editMember">确认</el-button>
            </span>
          </el-dialog>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      members: [],
      editMemberDialogVisible: false,
      memberForm: {
        memberID: '',
        memberName: '',
        telephone: ''
      }
    };
  },
  created() {
    this.fetchMembers();
  },
  methods: {

    async fetchMembers() {
      try {
        axios.get('api/member',{
          params:{

          }
        }).then((result)=>{
          this.members = result.data.data;
        })
        
      } catch (error) {
        this.$message.error('获取会员数据失败');
        console.error('获取会员数据失败', error);
      }
    },
    showEditMemberDialog(member) {
      console.log("赋值：",member);
      this.memberForm.memberID = member.memberID;
      this.memberForm.memberName = member.memberName;
      this.memberForm.telephone = member.telephone;

      this.editMemberDialogVisible = true;
    },
    async editMember() {
      try {
        axios.post('api/member/edit',this.memberForm)
        // console.log("编辑",this.memberForm);
        this.editMemberDialogVisible = false;
        this.$message.success('修改会员信息成功');
      } catch (error) {
        this.$message.error('修改会员信息失败');
        console.error('修改会员信息失败', error);
      }
    },
    async deleteMember(memberID) {
      try {
        axios.delete("api/memberdelete",{
          params:{
            memberID:memberID
          }
        }).then(()=>{
          this.$message.success('删除会员信息成功');
        })
      } catch (error) {
        this.$message.error('删除会员信息失败');
        console.error('删除会员信息失败', error);
      }
    }
  }
}
</script>
<style>
</style>