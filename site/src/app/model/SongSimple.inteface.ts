export interface SongSimple {
  id: number;
  title: string;
  image: string;
  artistId?: number;
  artistName?: string;
  albumId?: number;
  album?: string;
  styleId?: number;
  style?: string;
  date?: Date;
}
