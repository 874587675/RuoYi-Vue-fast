package com.ruoyi.project.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUpload {
    @ApiModelProperty(value = "需要上传的文件\n" +
            "支持上传的文件格式：文档：DOC、DOCX、XLS、XLSX、PPT、PPTX、PDF、Numbers、CSV\n" +
            "图片：JPG、JPG2、PNG、GIF、WEBP、HEIC、HEIF、BMP、PCD、TIFF\n" +
            "音频：WAV、MP3、FLAC、M4A、AAC、OGG、WMA、MIDI\n" +
            "视频：MP4、AVI、MOV、3GP、3GPP、FLV、WEBM、WMV、RMVB、M4V、MKV\n" +
            "文本文件：CPP、PY、JAVA、C\n" +
            "压缩包：RAR、ZIP、7Z、GZ、GZIP、BZ2\n" +
            "文件上传大小限制：每个文件最大 512 MB。\n" +
            "上传到扣子的文件仅限本账号查看或使用。\n" +
            "通过此接口上传的文件有效期为 3 个月。")
    public String file;
}
