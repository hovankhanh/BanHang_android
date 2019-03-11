<?php 
	include "connect.php";
	$query = "SELECT * FROM sanpham";
	$data = mysqli_query($conn, $query);
	$mangspmoinhat = array();


	// var_dump($data);
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangspmoinhat, new Sanphammoinhat(
			$row['id'],
			$row['tensanpham'],
			$row['giasanpham'],
			$row['hinhanhsanpham'],
			$row['motasanpham'],
			$row['idloaisanpham']));
	}

	echo json_encode($mangspmoinhat);


	class Sanphammoinhat{
		function Sanphammoinhat($id, $tensp, $giasp, $hinhanhsp, $motasp,$idloaisp){
			$this->id = $id;
			$this->tensp = $tensp;
			$this->giasp = $giasp;
			$this->hinhanhsp = $hinhanhsp;
			$this->motasp = $motasp;
			$this->idloaisp = $idloaisp;
			return $this;
		}
	}
 ?>