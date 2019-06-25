<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- popup for upload  -->
		<div class="modal fade" id="popupupload" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Upload your audio file</h4>
					</div>
					<div class="modal-body">
						<form action="InsertAudioServlet" method="POST" enctype="multipart/form-data">
							Audio name : <input type="text" class="form-control" name="audioname" placeholder="Audio name" /><br> Audio Description : <input type="text" class="form-control" name="audiodescription"
								placeholder="Audio Description" /><br> Audio File : <input type="file" name="fileaudio" size="50" /><br>
							<button type="submit" class="btn btn-primary">Submit</button>
						</form>
					</div>
					<!-- <div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						
					</div>  -->
				</div>
			</div>
		</div>