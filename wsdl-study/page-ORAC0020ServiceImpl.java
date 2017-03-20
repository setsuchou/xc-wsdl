package k5oraclerds.subsys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import k5oraclerds.subsys.common.Constants.ORACConstants;
import k5oraclerds.subsys.dao.ORAC0020FormMeisaiMapper;
import k5oraclerds.subsys.dao.Ｍ＿商品型Mapper;
import k5oraclerds.subsys.dao.Ｍ＿料金プランMapper;
import k5oraclerds.subsys.dao.Ｍ＿注文種別Mapper;
import k5oraclerds.subsys.dao.Ｔ＿契約情報Mapper;
import k5oraclerds.subsys.dao.Ｔ＿注文情報Mapper;
import k5oraclerds.subsys.dao.Ｔ＿注文明細Mapper;
import k5oraclerds.subsys.model.Ｍ＿商品型;
import k5oraclerds.subsys.model.Ｍ＿料金プラン;
import k5oraclerds.subsys.model.Ｍ＿注文種別;
import k5oraclerds.subsys.model.Ｔ＿契約情報;
import k5oraclerds.subsys.model.Ｔ＿注文情報;
import k5oraclerds.subsys.model.Ｔ＿注文明細;
import k5oraclerds.subsys.service.ORAC0020Service;
import k5oraclerds.subsys.webform.component.ORAC0020FormMeisai;

/**
 *
 * [契約情報検索]画面処理を行うサービス実現クラス
 *
 * @author setsuchou
 * @version 2017-03-03
 *
 */
@Service("ORAC0020Service")
public class ORAC0020ServiceImpl implements ORAC0020Service {

	@Autowired
	private Ｔ＿契約情報Mapper Ｔ＿契約情報Mapper;

	@Autowired
	private Ｔ＿注文情報Mapper Ｔ＿注文情報Mapper;

	@Autowired
	private Ｔ＿注文明細Mapper Ｔ＿注文明細Mapper;

	@Autowired
	private ORAC0020FormMeisaiMapper ORAC0020FormMeisaiMapper;

	@Autowired
	private Ｍ＿商品型Mapper Ｍ＿商品型Mapper;

	@Autowired
	private Ｍ＿料金プランMapper Ｍ＿料金プランMapper;

	@Autowired
	private Ｍ＿注文種別Mapper Ｍ＿注文種別Mapper;

	/**
	 * 契約情報、契約に紐付く注文情報、契約に紐付く注文明細を削除する
	 */
	public void sakujo(String ｋ５契約番号, String サービス申込番号, Short 連番) {

		// 契約情報を論理削除する
		Ｔ＿契約情報 Ｔ＿契約情報 = Ｔ＿契約情報Mapper.selectByPrimaryKey(ｋ５契約番号, サービス申込番号);
		Ｔ＿契約情報.set論理削除フラグ(ORACConstants.DEL_FLAG_DELETE);
		// 注文情報を物理削除する
		Ｔ＿注文情報Mapper.deleteByPrimaryKey(ｋ５契約番号, サービス申込番号, 連番);
		// 注文明細を物理削除する
		Ｔ＿注文明細Mapper.deleteByKeys(ｋ５契約番号, サービス申込番号, 連番);
	}

	@Override
	public Ｔ＿契約情報 getＴ＿契約情報ByPrimaryKey(String ｋ５契約番号, String サービス申込番号) {
		Ｔ＿契約情報 entity = Ｔ＿契約情報Mapper.selectByPrimaryKey(ｋ５契約番号, サービス申込番号);
		return entity;
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see
	 * k5oraclerds.subsys.service.commonSecletService#selectByPrimaryKey(java.
	 * lang.String, java.lang.String, java.lang.Short)
	 */
	@Override
	public Ｔ＿注文情報 selectByPrimaryKeyChumonJoho(String ｋ５契約番号, String サービス申込番号, Short 連番) {
		Ｔ＿注文情報 Ｔ＿注文情報 = Ｔ＿注文情報Mapper.selectByPrimaryKey(ｋ５契約番号, サービス申込番号, 連番);
		return Ｔ＿注文情報;
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see
	 * k5oraclerds.subsys.service.commonSecletService#selectByPrimaryKey(java.
	 * lang.String, java.lang.String, java.lang.Short, java.lang.String)
	 */
	@Override
	public Ｔ＿注文明細 selectByPrimaryKeyChumonjohoMeisai(String ｋ５契約番号, String サービス申込番号, Short 連番, String 商品型ｉｄ) {
		Ｔ＿注文明細 Ｔ＿注文明細 = Ｔ＿注文明細Mapper.selectByPrimaryKey(ｋ５契約番号, サービス申込番号, 連番, 商品型ｉｄ);
		return Ｔ＿注文明細;
	}

	@Override
	public List<ORAC0020FormMeisai> selectMeisaiByKeiyakuCondition(String sabisuMoshikomiBango, String k5keiyakubango,
			String aidenteiteiDmein, String sabisushuryoBiFrom, String sabisushuryoBiTo) {
		// List<ORAC0020FormMeisai> list = new ArrayList<ORAC0020FormMeisai>();
		PageHelper.startPage(1, 10);
		List<ORAC0020FormMeisai> list = ORAC0020FormMeisaiMapper.selectMeisaiByKeiyakuCondition(sabisuMoshikomiBango,
				k5keiyakubango, aidenteiteiDmein, sabisushuryoBiFrom, sabisushuryoBiTo);
		PageInfo page = new PageInfo(list);
		System.out.println(page.getPageNum());
		System.out.println(page.getPageSize());
		System.out.println(page.getTotal());
		System.out.println(page.getPages());
		System.out.println(page.getFirstPage());
		System.out.println(page.getLastPage());
		return list;
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see
	 * k5oraclerds.subsys.service.ORAC0020Service#selectMeisaiByChumonCondition(
	 * java.lang.String, java.lang.String, java.lang.Short, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<ORAC0020FormMeisai> selectMeisaiByChumonCondition(String サービス申込番号, String Ｋ５契約番号, Short 連番,
			String アイデンティティドメイン, String サービス終了日from, String サービス終了日to, String 適用開始希望日from, String 適用開始希望日to) {
		PageHelper.startPage(1, 10);
		List<ORAC0020FormMeisai> list = ORAC0020FormMeisaiMapper.selectMeisaiByChumonCondition(サービス申込番号, Ｋ５契約番号, 連番,
				アイデンティティドメイン, サービス終了日from, サービス終了日to, 適用開始希望日from, 適用開始希望日to);
		PageInfo page = new PageInfo(list);
		System.out.println("page.getPageNum():"+page.getPageNum());
		System.out.println("page.getPageSize():"+page.getPageSize());
		System.out.println("page.getStartRow():"+page.getStartRow());
		System.out.println("page.getTotal():"+page.getTotal());
		System.out.println("page.getPages():"+page.getPages());	
		
		return list;
	}

	@Override
	public List<Ｔ＿注文明細> selectChomonListByKeys(String ｋ５契約番号, String サービス申込番号, Short 連番) {
		PageHelper.startPage(1, 10);
		List<Ｔ＿注文明細> chumonMeisaiList = Ｔ＿注文明細Mapper.selectChomonListByKeys(ｋ５契約番号, サービス申込番号, 連番);
		PageInfo page = new PageInfo(chumonMeisaiList);
		System.out.println("page.getPageNum():"+page.getPageNum());
		System.out.println("page.getPageSize():"+page.getPageSize());
		System.out.println("page.getStartRow():"+page.getStartRow());
		System.out.println("page.getTotal():"+page.getTotal());
		System.out.println("page.getPages():"+page.getPages());
		
		return chumonMeisaiList;
	}

	@Override
	public List<Ｍ＿商品型> selectAllShohinGata() {
		List<Ｍ＿商品型> list = Ｍ＿商品型Mapper.selectAll();
		return list;
	}

	@Override
	public List<Ｍ＿料金プラン> selectAllRyokimPuran() {
		List<Ｍ＿料金プラン> list = Ｍ＿料金プランMapper.selectAll();
		return list;
	}

	@Override
	public List<Ｍ＿注文種別> selectAllChumonShubetsu() {
		List<Ｍ＿注文種別> list = Ｍ＿注文種別Mapper.selectAll();
		return list;
	}


}
